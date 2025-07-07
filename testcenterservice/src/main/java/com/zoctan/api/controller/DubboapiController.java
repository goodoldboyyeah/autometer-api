package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.dto.DeployunitModelDto;
import com.zoctan.api.entity.*;
import com.zoctan.api.service.DeployunitService;
import com.zoctan.api.service.DubboapiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.service.DubbomodelmethodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/09/26
 */
@RestController
@RequestMapping("/dubboapi")
public class DubboapiController {
    @Resource
    private DubboapiService dubboapiService;

    @Resource
    private DubbomodelmethodsService dubbomodelmethodsService;


    @Resource
    private DeployunitService deployunitService;


    @PostMapping
    public Result add(@RequestBody Dubboapi dubboapi) {
        dubboapiService.save(dubboapi);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/apibydeploy")
    public Result listbydeploy(@RequestParam long deployunitid, @RequestParam long modelid) {
        List<Dubboapi> list = dubboapiService.listAllbydeploy(deployunitid, modelid);
        return ResultGenerator.genOkResult(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dubboapiService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody Dubboapi dubboapi) {
        dubboapiService.update(dubboapi);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Dubboapi dubboapi = dubboapiService.getById(id);
        return ResultGenerator.genOkResult(dubboapi);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Dubboapi> list = dubboapiService.listAll();
        PageInfo<Dubboapi> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final Dubboapi recipe) {
        dubboapiService.updateDic(recipe);
        return ResultGenerator.genOkResult();
    }

    /**
     * 输入框查询
     */
    @PostMapping("/search")
    public Result search(@RequestBody final Map<String, Object> param) {
        Integer page = Integer.parseInt(param.get("page").toString());
        Integer size = Integer.parseInt(param.get("size").toString());
        PageHelper.startPage(page, size);
        final List<Dubboapi> list = dubboapiService.findDicWithName(param);
        final PageInfo<Dubboapi> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/exportapi")
    public Result exportapi(@RequestBody final List<DeployunitModelDto> deployunitModelList) {
        for (DeployunitModelDto deployunitModel : deployunitModelList) {
            long deployunitid = deployunitModel.getDeployunitid();
            Deployunit deployunit = deployunitService.getById(deployunitid);
            long modleid = deployunitModel.getId();
            Condition con = new Condition(Dubbomodelmethods.class);
            con.createCriteria().andCondition("modelid = " + modleid);
            List<Dubbomodelmethods> dubbomodelmethodsList = dubbomodelmethodsService.listByCondition(con);
            for (Dubbomodelmethods dubbomodelmethods : dubbomodelmethodsList) {
                String methodname = dubbomodelmethods.getMethodname();
                Condition con1 = new Condition(Dubboapi.class);
                con1.createCriteria().andCondition("deployunitid = " + deployunitid).andCondition("apiname = '" + methodname + "'");
                if (dubboapiService.listByCondition(con1).size() == 0) {
                    Dubboapi dubboapi = new Dubboapi();
                    dubboapi.setId(null);
                    dubboapi.setApiname(dubbomodelmethods.getMethodname());
                    dubboapi.setVersion(deployunitModel.getVersion());
                    dubboapi.setModelid(modleid);
                    dubboapi.setResponecontenttype("Basic");
                    dubboapi.setModelname(deployunitModel.getModelname());
                    dubboapi.setDeployunitid(deployunitModel.getDeployunitid());
                    dubboapi.setDeployunitname(deployunit.getDeployunitname());
                    dubboapi.setDgroup(deployunitModel.getDgroup());
                    dubboapi.setMemo("注册中心导入");
                    dubboapi.setCreateTime(new Date());
                    dubboapi.setLastmodifyTime(new Date());
                    dubboapi.setCasecounts(new Long(0));
                    dubboapi.setCreator(deployunitModel.getCreator());
                    dubboapi.setCreatorid(deployunitModel.getCreatorid());
                    dubboapi.setMid(deployunitModel.getMid());
                    dubboapi.setMnickname(deployunitModel.getMnickname());
                    dubboapiService.save(dubboapi);
                }
            }
        }
        return ResultGenerator.genOkResult();
    }
}
