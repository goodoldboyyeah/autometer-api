package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.entity.Dubboapi;
import com.zoctan.api.mapper.DubboapiMapper;
import com.zoctan.api.service.DubboapiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/09/26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DubboapiServiceImpl extends AbstractService<Dubboapi> implements DubboapiService {
    @Resource
    private DubboapiMapper dubboapiMapper;

    @Override
    public List<Dubboapi> findDicWithName(Map<String, Object> params) {
        return dubboapiMapper.findDicWithName(params);
    }

    @Override
    public int ifexist(Condition con) {
        return countByCondition(con);
    }


    @Override
    public void updateDic(Dubboapi params) {
        dubboapiMapper.updateDic(params);
    }

    @Override
    public List<Dubboapi> listAllbydeploy(long deployunitid, long modelid) {
        return dubboapiMapper.listAllbydeploy(deployunitid, modelid);
    }

}
