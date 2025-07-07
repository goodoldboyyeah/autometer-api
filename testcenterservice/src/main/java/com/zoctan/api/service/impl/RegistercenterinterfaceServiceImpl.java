package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.RegistercenterinterfaceMapper;
import com.zoctan.api.entity.Registercenterinterface;
import com.zoctan.api.service.RegistercenterinterfaceService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Condition;
import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/12
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RegistercenterinterfaceServiceImpl extends AbstractService<Registercenterinterface> implements RegistercenterinterfaceService {
@Resource
private RegistercenterinterfaceMapper registercenterinterfaceMapper;

@Override
public List<Registercenterinterface> findDicWithName(Map<String, Object> params) {
return registercenterinterfaceMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(Registercenterinterface params) {
registercenterinterfaceMapper.updateDic(params);
}

}
