package com.zoctan.api.service;

import com.zoctan.api.entity.Registercentermethods;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/12
*/
public interface RegistercentermethodsService extends Service<Registercentermethods> {
List<Registercentermethods> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(Registercentermethods params);
    void deletemethods(long interfaceid);
}
