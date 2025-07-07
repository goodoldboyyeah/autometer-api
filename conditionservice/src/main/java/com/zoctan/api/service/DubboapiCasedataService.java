package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.entity.DubboapiCasedata;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/30
 */
public interface DubboapiCasedataService extends Service<DubboapiCasedata> {
    List<DubboapiCasedata> findDicWithName(final Map<String, Object> params);

    int ifexist(Condition condition);

    void updateDic(DubboapiCasedata params);

    List<DubboapiCasedata> getcasedatabycaseid(final Map<String, Object> params);


}
