package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.Registercenterinterface;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface RegistercenterinterfaceMapper extends MyMapper<Registercenterinterface> {
    List<Registercenterinterface> findDicWithName(final Map<String, Object> params);
    int ifexist(Condition condition);
    void updateDic(Registercenterinterface params);
}