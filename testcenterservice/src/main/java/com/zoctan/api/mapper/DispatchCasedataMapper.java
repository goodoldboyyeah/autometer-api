package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.DispatchCasedata;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DispatchCasedataMapper extends MyMapper<DispatchCasedata> {
    List<DispatchCasedata> findDicWithName(final Map<String, Object> params);
    int ifexist(Condition condition);
    void updateDic(DispatchCasedata params);
}