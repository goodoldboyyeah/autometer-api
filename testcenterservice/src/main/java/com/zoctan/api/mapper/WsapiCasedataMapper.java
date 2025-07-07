package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.WsapiCasedata;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface WsapiCasedataMapper extends MyMapper<WsapiCasedata> {
    List<WsapiCasedata> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(WsapiCasedata params);

    List<WsapiCasedata> getcasedatabycaseid(final Map<String, Object> params);

    void deletcasedatabyid(Long caseid);

}