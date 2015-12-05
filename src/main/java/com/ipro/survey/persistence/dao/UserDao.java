package com.ipro.survey.persistence.dao;

import java.util.List;

import com.ipro.survey.persistence.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ipro.survey.persistence.model.SurveyQuestion;

/**
 * @Author weiqiang.yuan
 * @Time 15/8/7 18:30.
 */
@Repository
public interface UserDao {

    /**
     *
     * @param account
     * @return
     */
    User selectByAccount(@Param("account") String account);

    /**
     * 
     * @param user
     * @return
     */
    int insertUser(User user);


}
