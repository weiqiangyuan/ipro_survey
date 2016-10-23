package com.ipro.survey.persistence.dao.admin;

import com.ipro.survey.persistence.model.admin.Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by weiqiang.yuan on 16/6/19 15:42.
 */
@Repository
public interface AdministratorDao {

    @Insert({
            "insert into tb_administrator (account, password, admin_type) values (#{account}, #{password}, #{adminType})" })
    int insertAdministrator(Administrator administrator);

    @Select({ "select account, password, admin_type from tb_administrator where account = #{account}" })
    Administrator select(String account);

}
