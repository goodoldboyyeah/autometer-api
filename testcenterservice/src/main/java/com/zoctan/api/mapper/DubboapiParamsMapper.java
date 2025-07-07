package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.ApiParams;
import com.zoctan.api.entity.DubboapiParams;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DubboapiParamsMapper extends MyMapper<DubboapiParams> {
    List<DubboapiParams> findDicWithName(final Map<String, Object> params);
    List<DubboapiParams> getApiParamsbyid(long apiid);

    int ifexist(Condition condition);

    void updateDic(DubboapiParams params);

    void SaveApiParams(DubboapiParams apiParams);

    void updateApiParams(DubboapiParams params);

}