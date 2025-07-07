package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.DispatchDubboapicasedataMapper;
import com.zoctan.api.entity.DispatchDubboapicasedata;
import com.zoctan.api.service.DispatchDubboapicasedataService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/11/04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DispatchDubboapicasedataServiceImpl extends AbstractService<DispatchDubboapicasedata> implements DispatchDubboapicasedataService {
    @Resource
    private DispatchDubboapicasedataMapper dispatchDubboapicasedataMapper;

    @Override
    public List<DispatchDubboapicasedata> findDicWithName(Map<String, Object> params) {
        return dispatchDubboapicasedataMapper.findDicWithName(params);
    }

    @Override
    public int ifexist(Condition con) {
        return countByCondition(con);
    }


    @Override
    public void updateDic(DispatchDubboapicasedata params) {
        dispatchDubboapicasedataMapper.updateDic(params);
    }

}
