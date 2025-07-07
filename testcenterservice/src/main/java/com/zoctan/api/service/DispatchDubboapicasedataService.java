package com.zoctan.api.service;

import com.zoctan.api.entity.DispatchDubboapicasedata;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/11/04
*/
public interface DispatchDubboapicasedataService extends Service<DispatchDubboapicasedata> {
List<DispatchDubboapicasedata> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(DispatchDubboapicasedata params);
}
