package com.ipro.survey.service.security;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.ipro.survey.persistence.dao.admin.AdministratorDao;
import com.ipro.survey.persistence.model.admin.Administrator;
import com.ipro.survey.utils.StringBasicUtils;
import com.ipro.survey.utils.secret.DESUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by weiqiang.yuan on 16/6/26 15:27.
 */
@Service
public class SecurityService {

    private static final String SEPARATOR = "***";
    private static final long tokenAvailable = 3600000 * 24 * 30;

    private static Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private static Set<String> whileListUri = Sets.newHashSet();

    @Resource
    private AdministratorDao administratorDao;

    private Splitter splitter = Splitter.on(SecurityService.SEPARATOR).omitEmptyStrings().trimResults();

    static {
        whileListUri.add("/admin/createAdmin");
        whileListUri.add("/admin/login");
    }

    public String checkUser(String token) {

//        String realToken = DESUtils.decryptBasedDes(token);
        String realToken = new String(Base64.decodeBase64(token));

        logger.info("realToken={}", realToken);
        List<String> strings = splitter.splitToList(realToken);
        String account = strings.get(0);
        String password = strings.get(1);
        String timeStamp = strings.get(2);
        Administrator select = administratorDao.select(account);
        if (select == null) {
            return "用户不存在";
        }
        String accountDB = select.getAccount();
        String passwordDB = select.getPassword();
        logger.info("check account={} accountDB={} pwd={} pwdDB={}", account, accountDB, password, passwordDB);
        if (!Objects.equals(account, accountDB) || !Objects.equals(password, passwordDB)) {
            return "用户信息错误,无法访问";
        }
        if (System.currentTimeMillis() - Long.valueOf(timeStamp) < tokenAvailable) {
            return "token 已经过期";
        }
        return "";
    }


    public Pair<String,String> getAccountAndPwdFromToken(String token) {
//        String realToken = DESUtils.decryptBasedDes(token);
        String realToken = new String(Base64.decodeBase64(token));

        logger.info("realToken={}", realToken);
        List<String> strings = splitter.splitToList(realToken);
        String account = strings.get(0);
        String password = strings.get(1);
        return Pair.of(account, password);
    }

    public static String generateToken(String account, String password) {
        long time = System.currentTimeMillis();
        logger.info("generateToken param = {} {} {}", account, password, time);
        String originToken = account + SEPARATOR + password + SEPARATOR + time;
//        String secretToken = DESUtils.encryptBasedDes(originToken);
        String secretToken = Base64.encodeBase64String(originToken.getBytes());

        logger.info("secretToken from {} to {}", originToken, secretToken);
        return secretToken;
    }

    public static boolean isInWhiteList(String url) {
        return whileListUri.contains(url);
    }

}
