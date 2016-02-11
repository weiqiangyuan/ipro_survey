package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.UserProjectRef;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface UserProjectRefDao {

    UserProjectRef selectByProjectUniqNo(@Param("projectUniqNo") String projectUniqNo);

    List<UserProjectRef> selectByUserAccount(@Param("userAccount") String userAccount);

}
