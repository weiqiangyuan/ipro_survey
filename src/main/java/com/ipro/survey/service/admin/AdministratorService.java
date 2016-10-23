package com.ipro.survey.service.admin;

import com.ipro.survey.persistence.dao.admin.AdministratorDao;
import com.ipro.survey.persistence.model.admin.Administrator;
import com.ipro.survey.utils.secret.DESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 16/6/19 16:51.
 */
@Service
public class AdministratorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AdministratorDao administratorDao;

    public void insertAdministrator(Administrator administrator) {
        logger.info("createAdmin param = {}", administrator);
        int ret = administratorDao.insertAdministrator(administrator);
        logger.info("insert administrator={} ret={}", administrator, ret);
    }



//    public void checkAdministrator(String account, String password) {
//        logger.info("createAdmin param = {}", administrator);
//        int ret = administratorDao.insertAdministrator(administrator);
//        logger.info("insert administrator={} ret={}", administrator, ret);
//    }

}
