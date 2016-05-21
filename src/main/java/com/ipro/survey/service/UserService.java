package com.ipro.survey.service;

import com.ipro.survey.Enum.TaskStatus;
import com.ipro.survey.Enum.UserType;
import com.ipro.survey.exception.ProjectException;
import com.ipro.survey.exception.UserException;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.dao.UserDao;
import com.ipro.survey.persistence.dao.UserProjectRefDao;
import com.ipro.survey.persistence.dao.project.HealthProjectDao;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.persistence.model.User;
import com.ipro.survey.persistence.model.UserProjectRef;
import com.ipro.survey.persistence.model.project.HealthProject;
import com.ipro.survey.service.message.MessageGenerateService;
import com.ipro.survey.service.thread.NotifyMessageWorker;
import com.ipro.survey.utils.UniqueKeyUtil;
import com.ipro.survey.web.vo.user.UserDetailVO;
import com.ipro.survey.web.vo.user.UserProjectDetail;
import com.ipro.survey.web.vo.user.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by weiqiang.yuan on 15/12/5 15:31.
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserDao userDao;
    @Resource
    private UserProjectRefDao userProjectRefDao;
    @Resource
    private HealthProjectDao healthProjectDao;
    @Resource
    private ProjectTaskService projectTaskService;
    @Resource
    private MessageGenerateService messageGenerateService;
    @Resource
    private ProjectTaskDao projectTaskDao;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void insertUser(UserVO userVO) {
        // try {
        User user = new User();
        user.setStatus(1);
        user.setType(UserType.codeOf(userVO.getType()));
        user.setNickName(userVO.getNickName());
        user.setAccount(userVO.getAccount());
        logger.info("保存用户{}", user);
        int ret = userDao.insertUser(user);
        logger.info("保存用户结果{}", user, ret);
        // } catch (Exception e) {
        // logger.error("创建用户异常{}", userVO, e);
        // throw new UserException("创建用户异常");
        // }
    }

    public boolean checkUserIfExist(String userAccount) {
        User user = userDao.selectByAccount(userAccount);
        if (user == null) {
            logger.info("userAccount={} is null", userAccount);
            // UserVO userVO = new UserVO();
            // userVO.setAccount(userAccount);
            // userVO.setNickName(nickName);
            // userVO.setType(1);
            // userVO.setStatus(1);
            // insertUser(userVO);
            return false;
        } else {
            logger.info("user ={} exist no need to create", userAccount);
            return true;
        }
    }

    public UserDetailVO showUserDetail(String userAccount) {
        logger.info("show userDetail={}", userAccount);
        User user = userDao.selectByAccount(userAccount);
        UserDetailVO userDetailVO = new UserDetailVO();
        if (user == null) {
            userDetailVO.setStatus(3);
            return userDetailVO;
        }

        userDetailVO.setUserAccount(user.getAccount());
        userDetailVO.setUserNickName(user.getNickName());

        List<UserProjectRef> userProjectRefList = userProjectRefDao.selectByUserAccount(userAccount);
        if (CollectionUtils.isEmpty(userProjectRefList)) {
            UserProjectDetail userProjectDetail = new UserProjectDetail();
            userProjectDetail.setHaveProject(false);
            userDetailVO.setUserProjectDetail(userProjectDetail);
            userDetailVO.setStatus(1);
            return userDetailVO;
        }
        String projectNo = "";
        String projectUniqNo = "";
        for (UserProjectRef userProjectRef : userProjectRefList) {
            if (userProjectRef.getStatus().equals(1)) {
                projectNo = userProjectRef.getProjectNo();
                projectUniqNo = userProjectRef.getProjectUniqNo();
                break;
            }
        }
        if (StringUtils.isBlank(projectNo)) {
            UserProjectDetail userProjectDetail = new UserProjectDetail();
            userProjectDetail.setHaveProject(false);
            userDetailVO.setUserProjectDetail(userProjectDetail);
            userDetailVO.setStatus(1);
            return userDetailVO;
        }
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);

        UserProjectDetail userProjectDetail = new UserProjectDetail();
        userProjectDetail.setProjectName(healthProject.getProjectName());
        userProjectDetail.setProjectUniqNo(projectUniqNo);
        userProjectDetail.setHaveProject(true);
        List<ProjectTask> projectTasks = projectTaskDao.selectUserAllTask(projectUniqNo, userAccount);
        double done = 0;
        double allTaskNum = CollectionUtils.isEmpty(projectTasks) ? 1 : projectTasks.size();
        for (ProjectTask projectTask : projectTasks) {
            if (projectTask.getStatus() == TaskStatus.DONE) {
                done++;
            }
        }
        BigDecimal percent = new BigDecimal((done / allTaskNum) * 100);

        userProjectDetail.setProgressRate(percent.setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"%");
        userDetailVO.setUserProjectDetail(userProjectDetail);
        userDetailVO.setStatus(2);
        return userDetailVO;

    }

    public static void main(String[] args) {
        System.out.println(9d / 11d);
    }

    @Transactional
    public String participateProject(String userAccount, String projectNo) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        if (healthProject == null) {
            throw new UserException("该项目不存在");
        }
        User user = userDao.selectByAccount(userAccount);
        if (user == null) {
            throw new UserException("该用户不存在");
        }
        List<UserProjectRef> userProjectRefList = userProjectRefDao.selectValidTaskByAccountAndProjectNo(userAccount,
                projectNo);
        if (CollectionUtils.isNotEmpty(userProjectRefList)) {
            throw new UserException("参加项目失败, 用户参加的当前项目尚未完成");
        }
        UserProjectRef userProjectRef = new UserProjectRef();
        userProjectRef.setStatus(1);
        userProjectRef.setProjectNo(projectNo);
        userProjectRef.setUserAccount(userAccount);
        String projectUniqNo = UniqueKeyUtil.generateProjectUniqNo(projectNo);
        userProjectRef.setProjectUniqNo(projectUniqNo);
        int i = userProjectRefDao.insertUserProjectRef(userProjectRef);
        logger.info("insert user ret = {} projectUniqNo = {}", i, projectUniqNo);

        projectTaskService.createUserTaskList(projectUniqNo, userAccount);

        NotifyMessageWorker notifyMessageWorker = new NotifyMessageWorker(userAccount, projectUniqNo,
                messageGenerateService);

        executorService.submit(notifyMessageWorker);

        return projectUniqNo;

    }

    public void quitProject(String userAccount, String projectUniqNo) {
        int i = userProjectRefDao.invalidByUserAccountAndProjectUniqNo(userAccount, projectUniqNo);
        if (i <= 0) {
            throw new ProjectException("取消project失败");
        }
    }

}
