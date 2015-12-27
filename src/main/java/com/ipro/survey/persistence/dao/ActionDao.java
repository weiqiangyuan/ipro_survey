package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.Action;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface ActionDao {

    Action selectByActionNo(@Param("actionNo") String actionNo);

    List<Action> selectActionList();

    int insertAction(Action action);

}
