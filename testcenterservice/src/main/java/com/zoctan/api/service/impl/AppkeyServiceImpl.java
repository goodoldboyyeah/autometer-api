package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.AppkeyMapper;
import com.zoctan.api.entity.Appkey;
import com.zoctan.api.service.AppkeyService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Condition;
import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/09/07
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class AppkeyServiceImpl extends AbstractService<Appkey> implements AppkeyService {
@Resource
private AppkeyMapper appkeyMapper;

@Override
public List<Appkey> findDicWithName(Map<String, Object> params) {
return appkeyMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(Appkey params) {
appkeyMapper.updateDic(params);
}

}
