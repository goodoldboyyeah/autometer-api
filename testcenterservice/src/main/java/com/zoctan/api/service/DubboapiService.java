package com.zoctan.api.service;

import com.zoctan.api.entity.Api;
import com.zoctan.api.entity.Dubboapi;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/09/26
 */
public interface DubboapiService extends Service<Dubboapi> {
    List<Dubboapi> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(Dubboapi params);

    void updateApibymodelid(long modelid,String modelname,String version,String dgroup);


    List<Dubboapi> listAllbydeploy(long deployunitid, long modelid);

}
