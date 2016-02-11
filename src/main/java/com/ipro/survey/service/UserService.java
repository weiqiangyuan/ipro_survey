package com.ipro.survey.service;

import com.ipro.survey.Enum.UserType;
import com.ipro.survey.exception.UserException;
import com.ipro.survey.persistence.dao.UserDao;
import com.ipro.survey.persistence.dao.UserProjectRefDao;
import com.ipro.survey.persistence.dao.project.HealthProjectDao;
import com.ipro.survey.persistence.model.User;
import com.ipro.survey.persistence.model.UserProjectRef;
import com.ipro.survey.persistence.model.project.HealthProject;
import com.ipro.survey.utils.UniqueKeyUtil;
import com.ipro.survey.web.vo.user.UserDetailVO;
import com.ipro.survey.web.vo.user.UserProjectDetail;
import com.ipro.survey.web.vo.user.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public void insertUser(UserVO userVO) {
        try {
            User user = new User();
            user.setStatus(userVO.getStatus());
            user.setType(UserType.codeOf(userVO.getType()));
            user.setNickName(userVO.getNickName());
            user.setAccount(userVO.getAccount());
            logger.info("保存用户{}", user);
            int ret = userDao.insertUser(user);
            logger.info("保存用户结果{}", user, ret);
        } catch (Exception e) {
            logger.error("创建用户异常{}", userVO, e);
        }
    }

    public void createUserIfNotExist(String userAccount, String nickName) {
        User user = userDao.selectByAccount(userAccount);
        if (user == null) {
            logger.info("userAccount={} is null", userAccount);
            UserVO userVO = new UserVO();
            userVO.setAccount(userAccount);
            userVO.setNickName(nickName);
            userVO.setType(1);
            userVO.setStatus(1);
            insertUser(userVO);
        } else {
            logger.info("user ={} exist no need to create", userAccount);
        }
    }

    public UserDetailVO showUserDetail(String userAccount) {
        logger.info("show userDetail={}", userAccount);
        User user = userDao.selectByAccount(userAccount);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setUserAccount(user.getAccount());
        userDetailVO.setUserNickName(user.getNickName());

        UserProjectDetail userProjectDetail = new UserProjectDetail();
        List<UserProjectRef> userProjectRefList = userProjectRefDao.selectByUserAccount(userAccount);
        if (CollectionUtils.isEmpty(userProjectRefList)) {
            userProjectDetail.setHaveProject(false);
            userDetailVO.setUserProjectDetail(userProjectDetail);
            return userDetailVO;
        }
        String projectNo = "";
        String projectUniqNo = "";
        for (UserProjectRef userProjectRef : userProjectRefList) {
            if (userProjectRef.getStatus().equals(1)) {
                projectNo = userProjectRef.getProjectNo();
                projectUniqNo = userProjectRef.getProjectUniqNo();
            }
        }
        if (StringUtils.isBlank(projectNo)) {
            userProjectDetail.setHaveProject(false);
            userDetailVO.setUserProjectDetail(userProjectDetail);
            return userDetailVO;
        }
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        userProjectDetail.setProjectName(healthProject.getProjectName());
        userProjectDetail.setProjectUniqNo(projectUniqNo);
        userDetailVO.setUserProjectDetail(userProjectDetail);
        logger.info("userDetailVO={}", userDetailVO);
        return userDetailVO;

    }

    public void participateProject(String userAccount, String projectNo) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        if(healthProject == null) {
            throw new UserException("该项目不存在");
        }
        User user = userDao.selectByAccount(userAccount);
        if(user == null) {
            throw new UserException("该用户不存在");
        }
        List<UserProjectRef> userProjectRefList = userProjectRefDao.selectValidTaskByAccountAndProjectNo(userAccount, projectNo);
        if (CollectionUtils.isNotEmpty(userProjectRefList)) {
            throw new UserException("参加项目失败, 用户参加的当前项目尚未完成");
        }
        UserProjectRef userProjectRef = new UserProjectRef();
        userProjectRef.setStatus(1);
        userProjectRef.setProjectNo(projectNo);
        userProjectRef.setUserAccount(userAccount);
        userProjectRef.setProjectUniqNo(UniqueKeyUtil.generateProjectUniqNo(projectNo));
        int i = userProjectRefDao.insertUserProjectRef(userProjectRef);
        logger.info("insert user ret = {}", i);
    }

}
