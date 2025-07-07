package com.zoctan.api.service;

import com.zoctan.api.entity.DispatchCasedata;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/08/23
*/
public interface DispatchCasedataService extends Service<DispatchCasedata> {
List<DispatchCasedata> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(DispatchCasedata params);
}
