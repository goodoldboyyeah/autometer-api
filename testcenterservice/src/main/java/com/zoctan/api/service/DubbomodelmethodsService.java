package com.zoctan.api.service;

import com.zoctan.api.entity.Dubbomodelmethods;
import com.zoctan.api.core.service.Service;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/10
*/
public interface DubbomodelmethodsService extends Service<Dubbomodelmethods> {
List<Dubbomodelmethods> findDicWithName(final Map<String, Object> params);
int ifexist(Condition condition);
void updateDic(Dubbomodelmethods params);
    void deletemethods(@Param("modelid")long modelid);
}
