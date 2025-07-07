package com.zoctan.api.service;

import com.zoctan.api.entity.Api;
import com.zoctan.api.entity.Wsapi;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2025/02/18
 */
public interface WsapiService extends Service<Wsapi> {
    List<Wsapi> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(Wsapi params);

    List<Wsapi> listAllbydeploy(long deployunitid, long modelid);

}
