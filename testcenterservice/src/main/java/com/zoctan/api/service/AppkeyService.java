package com.zoctan.api.service;

import com.zoctan.api.entity.Appkey;
import com.zoctan.api.core.service.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/09/07
*/
public interface AppkeyService extends Service<Appkey> {
List<Appkey> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(Appkey params);
}
