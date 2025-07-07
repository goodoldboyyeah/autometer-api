package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.DubboapiCasedata;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DubboapiCasedataMapper extends MyMapper<DubboapiCasedata> {
    List<DubboapiCasedata> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(DubboapiCasedata params);

    List<DubboapiCasedata> getcasedatabycaseid(final Map<String, Object> params);

}