package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.RegistercentermethodsMapper;
import com.zoctan.api.entity.Registercentermethods;
import com.zoctan.api.service.RegistercentermethodsService;
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
public class RegistercentermethodsServiceImpl extends AbstractService<Registercentermethods> implements RegistercentermethodsService {
@Resource
private RegistercentermethodsMapper registercentermethodsMapper;

@Override
public List<Registercentermethods> findDicWithName(Map<String, Object> params) {
return registercentermethodsMapper.findDicWithName(params);
}

@Override
public int ifexist(Condition con) {
return countByCondition(con);
}


@Override
public void updateDic(Registercentermethods params) {
registercentermethodsMapper.updateDic(params);
}

    @Override
    public void deletemethods(long interfaceid) {
        registercentermethodsMapper.deletemethods(interfaceid);
    }

}
