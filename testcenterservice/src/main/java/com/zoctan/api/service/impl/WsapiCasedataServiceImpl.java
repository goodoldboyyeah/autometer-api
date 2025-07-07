package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.WsapiCasedataMapper;
import com.zoctan.api.entity.WsapiCasedata;
import com.zoctan.api.service.WsapiCasedataService;
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
public class WsapiCasedataServiceImpl extends AbstractService<WsapiCasedata> implements WsapiCasedataService {
@Resource
private WsapiCasedataMapper wsapiCasedataMapper;

@Override
public List<WsapiCasedata> findDicWithName(Map<String, Object> params) {
return wsapiCasedataMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(WsapiCasedata params) {
wsapiCasedataMapper.updateDic(params);
}

    @Override
    public List<WsapiCasedata> getcasedatabycaseid(Map<String, Object> params) {
        return wsapiCasedataMapper.getcasedatabycaseid(params);
    }

    @Override
    public void deletcasedatabyid(Long caseid) {
        wsapiCasedataMapper.deletcasedatabyid(caseid);
    }

}
