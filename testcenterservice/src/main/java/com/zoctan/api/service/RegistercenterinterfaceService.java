package com.zoctan.api.service;

import com.zoctan.api.entity.Registercenterinterface;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/12
*/
public interface RegistercenterinterfaceService extends Service<Registercenterinterface> {
List<Registercenterinterface> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(Registercenterinterface params);
}
