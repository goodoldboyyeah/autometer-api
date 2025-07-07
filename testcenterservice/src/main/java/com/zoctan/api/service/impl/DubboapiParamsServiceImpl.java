package com.zoctan.api.service.impl;

import com.zoctan.api.entity.ApiParams;
import com.zoctan.api.mapper.DubboapiParamsMapper;
import com.zoctan.api.entity.DubboapiParams;
import com.zoctan.api.service.DubboapiParamsService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DubboapiParamsServiceImpl extends AbstractService<DubboapiParams> implements DubboapiParamsService {
    @Resource
    private DubboapiParamsMapper dubboapiParamsMapper;

    @Override
    public List<DubboapiParams> findDicWithName(Map<String, Object> params) {
        return dubboapiParamsMapper.findDicWithName(params);
    }

    @Override
    public List<DubboapiParams> getApiParamsbyid(long apiid) {
        return dubboapiParamsMapper.getApiParamsbyid(apiid);
    }

    @Override
    public int ifexist(Condition con) {
        return countByCondition(con);
    }


    @Override
    public void updateDic(DubboapiParams params) {
        dubboapiParamsMapper.updateDic(params);
    }

    @Override
    public void SaveApiParams(DubboapiParams apiParams) {
        dubboapiParamsMapper.SaveApiParams(apiParams);
    }

    @Override
    public void updateApiParams(DubboapiParams params) {
        dubboapiParamsMapper.updateApiParams(params);
    }

}
