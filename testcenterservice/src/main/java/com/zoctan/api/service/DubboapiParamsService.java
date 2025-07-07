package com.zoctan.api.service;

import com.zoctan.api.entity.ApiParams;
import com.zoctan.api.entity.DubboapiParams;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/27
 */
public interface DubboapiParamsService extends Service<DubboapiParams> {
    List<DubboapiParams> findDicWithName(final Map<String, Object> params);
    List<DubboapiParams> getApiParamsbyid(long apiid);

    int ifexist(Condition condition);

    void updateDic(DubboapiParams params);

    void SaveApiParams(DubboapiParams apiParams);

    void updateApiParams(DubboapiParams params);


}
