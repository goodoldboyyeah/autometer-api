package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.Api;
import com.zoctan.api.entity.Wsapi;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface WsapiMapper extends MyMapper<Wsapi> {
    List<Wsapi> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(Wsapi params);

    List<Wsapi> listAllbydeploy(long deployunitid, long modelid);

}