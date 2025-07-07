package com.zoctan.api.service;

import com.zoctan.api.entity.ApiParams;
import com.zoctan.api.entity.WsapiParams;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2025/02/18
 */
public interface WsapiParamsService extends Service<WsapiParams> {
    List<WsapiParams> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(WsapiParams params);

    List<WsapiParams> getApiParamsbyapiid(final Map<String, Object> params);

    void deletebyApiid(Long apiid);

}
