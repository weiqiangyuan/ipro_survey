package com.ipro.survey.service;

import com.ipro.survey.persistence.dao.UserDao;
import com.ipro.survey.persistence.model.User;
import com.ipro.survey.web.vo.UserVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 15/12/5 15:31.
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserDao userDao;

    public void insertWechatUser(UserVO userVO) {
        try {
            User user = new User();
            BeanUtils.copyProperties(user, userVO);
            logger.info("保存用户{}", user);
            int ret = userDao.insertUser(user);
            logger.info("保存用户结果{}", user, ret);
        } catch (Exception e) {
            logger.error("创建用户异常{}", userVO, e);
        }
    }
}
