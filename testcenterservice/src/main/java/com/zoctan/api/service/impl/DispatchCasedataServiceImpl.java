package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.DispatchCasedataMapper;
import com.zoctan.api.entity.DispatchCasedata;
import com.zoctan.api.service.DispatchCasedataService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Condition;
import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/08/23
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DispatchCasedataServiceImpl extends AbstractService<DispatchCasedata> implements DispatchCasedataService {
@Resource
private DispatchCasedataMapper dispatchCasedataMapper;

@Override
public List<DispatchCasedata> findDicWithName(Map<String, Object> params) {
return dispatchCasedataMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(DispatchCasedata params) {
dispatchCasedataMapper.updateDic(params);
}

}
