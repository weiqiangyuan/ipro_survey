package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    /**
     * 根据条件查询用户数量
     * 
     * @param user
     * @return
     */
    int countUserByCondition(@Param("user") User user);

}
