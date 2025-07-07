package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.WsapiMapper;
import com.zoctan.api.entity.Wsapi;
import com.zoctan.api.service.WsapiService;
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
public class WsapiServiceImpl extends AbstractService<Wsapi> implements WsapiService {
@Resource
private WsapiMapper wsapiMapper;

@Override
public List<Wsapi> findDicWithName(Map<String, Object> params) {
return wsapiMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(Wsapi params) {
wsapiMapper.updateDic(params);
}

    @Override
    public List<Wsapi> listAllbydeploy(long deployunitid, long modelid) {
        return wsapiMapper.listAllbydeploy(deployunitid, modelid);
    }

}
