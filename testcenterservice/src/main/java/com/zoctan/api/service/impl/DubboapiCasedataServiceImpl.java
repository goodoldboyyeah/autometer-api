package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.DubboapiCasedataMapper;
import com.zoctan.api.entity.DubboapiCasedata;
import com.zoctan.api.service.DubboapiCasedataService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DubboapiCasedataServiceImpl extends AbstractService<DubboapiCasedata> implements DubboapiCasedataService {
    @Resource
    private DubboapiCasedataMapper dubboapiCasedataMapper;

    @Override
    public List<DubboapiCasedata> findDicWithName(Map<String, Object> params) {
        return dubboapiCasedataMapper.findDicWithName(params);
    }

    @Override
    public int ifexist(Condition con) {
        return countByCondition(con);
    }


    @Override
    public void updateDic(DubboapiCasedata params) {
        dubboapiCasedataMapper.updateDic(params);
    }

    @Override
    public List<DubboapiCasedata> getcasedatabycaseid(Map<String, Object> params) {
        return dubboapiCasedataMapper.getcasedatabycaseid(params);
    }

    @Override
    public void deletcasedatabyid(Long caseid) {
        dubboapiCasedataMapper.deletcasedatabyid(caseid);
    }

}
