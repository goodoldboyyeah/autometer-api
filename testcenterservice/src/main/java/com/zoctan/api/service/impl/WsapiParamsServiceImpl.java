package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.WsapiParamsMapper;
import com.zoctan.api.entity.WsapiParams;
import com.zoctan.api.service.WsapiParamsService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2025/02/18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WsapiParamsServiceImpl extends AbstractService<WsapiParams> implements WsapiParamsService {
    @Resource
    private WsapiParamsMapper wsapiParamsMapper;

    @Override
    public List<WsapiParams> findDicWithName(Map<String, Object> params) {
        return wsapiParamsMapper.findDicWithName(params);
    }

    @Override
    public int ifexist(Condition con) {
        return countByCondition(con);
    }


    @Override
    public void updateDic(WsapiParams params) {
        wsapiParamsMapper.updateDic(params);
    }

    @Override
    public List<WsapiParams> getApiParamsbyapiid(Map<String, Object> params) {
        return wsapiParamsMapper.getApiParamsbyapiid(params);
    }

    @Override
    public void deletebyApiid(Long apiid) {
        wsapiParamsMapper.deletebyApiid(apiid);
    }

}
