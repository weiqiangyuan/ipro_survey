package com.ipro.survey.service.action;

import com.google.common.collect.Lists;
import com.ipro.survey.persistence.dao.ActionDao;
import com.ipro.survey.persistence.model.Action;
import com.ipro.survey.service.transform.TransformModel;
import com.ipro.survey.web.vo.action.ActionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/26 17:05.
 */
@Service
public class ActionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ActionDao actionDao;

    public List<ActionVO> getActionVOList() {
        List<Action> actionList = actionDao.selectActionList();
        List<ActionVO> transform = Lists.transform(actionList, TransformModel.TO_ACTION_VO);
        logger.info("action from db={} vo={}", actionList, transform);
        return transform;
    }

}
