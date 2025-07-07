package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.Dubbomodelmethods;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DubbomodelmethodsMapper extends MyMapper<Dubbomodelmethods> {
    void deletemethods(long modelid);
    List<Dubbomodelmethods> findDicWithName(final Map<String, Object> params);
    int ifexist(Condition condition);
    void updateDic(Dubbomodelmethods params);

}