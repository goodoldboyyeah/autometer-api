package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.DubbomodelmethodsMapper;
import com.zoctan.api.entity.Dubbomodelmethods;
import com.zoctan.api.service.DubbomodelmethodsService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Condition;
import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/10
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DubbomodelmethodsServiceImpl extends AbstractService<Dubbomodelmethods> implements DubbomodelmethodsService {
@Resource
private DubbomodelmethodsMapper dubbomodelmethodsMapper;

@Override
public List<Dubbomodelmethods> findDicWithName(Map<String, Object> params) {
return dubbomodelmethodsMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(Dubbomodelmethods params) {
dubbomodelmethodsMapper.updateDic(params);
}

    @Override
    public void deletemethods(long modelid) {
        dubbomodelmethodsMapper.deletemethods(modelid);
    }

}
