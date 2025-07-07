package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.DispatchDubboapicasedata;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DispatchDubboapicasedataMapper extends MyMapper<DispatchDubboapicasedata> {
    List<DispatchDubboapicasedata> findDicWithName(final Map<String, Object> params);
    int ifexist(Condition condition);
    void updateDic(DispatchDubboapicasedata params);
}