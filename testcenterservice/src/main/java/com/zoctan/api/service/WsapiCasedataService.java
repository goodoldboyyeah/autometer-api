package com.zoctan.api.service;

import com.zoctan.api.entity.DubboapiCasedata;
import com.zoctan.api.entity.WsapiCasedata;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2025/02/18
 */
public interface WsapiCasedataService extends Service<WsapiCasedata> {
    List<WsapiCasedata> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(WsapiCasedata params);

    List<WsapiCasedata> getcasedatabycaseid(final Map<String, Object> params);

    void deletcasedatabyid(Long caseid);

}
