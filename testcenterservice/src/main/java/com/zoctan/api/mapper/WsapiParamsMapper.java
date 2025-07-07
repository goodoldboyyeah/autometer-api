package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.WsapiParams;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface WsapiParamsMapper extends MyMapper<WsapiParams> {
    List<WsapiParams> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(WsapiParams params);

    List<WsapiParams> getApiParamsbyapiid(final Map<String, Object> params);

    void deletebyApiid(Long apiid);
}