package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.Api;
import com.zoctan.api.entity.Apicases;
import com.zoctan.api.entity.Deployunit;
import com.zoctan.api.entity.Wsapi;
import com.zoctan.api.service.DeployunitService;
import com.zoctan.api.service.WsapiParamsService;
import com.zoctan.api.service.WsapiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2025/02/18
 */
@RestController
@RequestMapping("/wsapi")
public class WsapiController {
    @Resource
    private WsapiService wsapiService;

    @Resource
    private WsapiParamsService wsapiParamsService;


    @Resource
    private DeployunitService deployunitService;

    @PostMapping
    public Result add(@RequestBody Wsapi wsapi) {
        wsapiService.save(wsapi);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/apibydeploy")
    public Result listbydeploy(@RequestParam long deployunitid, @RequestParam long modelid) {
        List<Wsapi> list = wsapiService.listAllbydeploy(deployunitid, modelid);
        return ResultGenerator.genOkResult(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
//        List<Apicases> apicasesList = apicasesService.getcasebyapiid(id);
//        if (apicasesList.size() > 0) {
//            return ResultGenerator.genFailedResult("当前API还存在测试用例，无法删除,请先删除对应的测试用例");
//        } else {
//            Wsapi api = wsapiService.getById(id);
//            wsapiService.deleteById(id);
//            wsapiParamsService.deletebyApiid(id);
//            long deployid = api.getDeployunitid();
//            Deployunit deployunit = deployunitService.getById(deployid);
//            deployunit.setApicounts(deployunit.getApicounts() - 1);
//            deployunitService.update(deployunit);
//            return ResultGenerator.genOkResult();
//        }
                    return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody Wsapi wsapi) {
        wsapiService.update(wsapi);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Wsapi wsapi = wsapiService.getById(id);
        return ResultGenerator.genOkResult(wsapi);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Wsapi> list = wsapiService.listAll();
        PageInfo<Wsapi> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final Wsapi params) {
        wsapiService.updateDic(params);
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
        final List<Wsapi> list = wsapiService.findDicWithName(param);
        final PageInfo<Wsapi> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
