package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.entity.Dubboapi;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

public interface DubboapiMapper extends MyMapper<Dubboapi> {
    List<Dubboapi> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(Dubboapi params);

    void updateApibymodelid(@Param("modelid")long modelid, @Param("modelname")String modelname, @Param("version")String version, @Param("dgroup")String dgroup);


    List<Dubboapi> listAllbydeploy(long deployunitid, long modelid);

}