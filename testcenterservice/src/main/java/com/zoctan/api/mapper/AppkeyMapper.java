package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.Appkey;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface AppkeyMapper extends MyMapper<Appkey> {
    List<Appkey> findDicWithName(final Map<String, Object> params);
    int ifexist(Condition condition);
    void updateDic(Appkey params);
}