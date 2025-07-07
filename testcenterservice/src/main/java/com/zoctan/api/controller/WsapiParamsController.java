package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.ApiParams;
import com.zoctan.api.entity.WsapiParams;
import com.zoctan.api.service.WsapiParamsService;
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
@RequestMapping("/wsapi/params")
public class WsapiParamsController {
    @Resource
    private WsapiParamsService wsapiParamsService;

    @PostMapping
    public Result add(@RequestBody WsapiParams wsapiParams) {
        Condition con = new Condition(WsapiParams.class);
        con.createCriteria().andCondition("apiid = " + wsapiParams.getApiid());
        if (wsapiParamsService.ifexist(con) == 0) {
            wsapiParamsService.save(wsapiParams);
        } else {
            wsapiParamsService.update(wsapiParams);
        }
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        wsapiParamsService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody WsapiParams wsapiParams) {
        wsapiParamsService.update(wsapiParams);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        WsapiParams wsapiParams = wsapiParamsService.getById(id);
        return ResultGenerator.genOkResult(wsapiParams);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WsapiParams> list = wsapiParamsService.listAll();
        PageInfo<WsapiParams> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final WsapiParams params) {

        wsapiParamsService.updateDic(params);
        return ResultGenerator.genOkResult();
    }


    @PostMapping("/searchparamsbyapiid")
    public Result searchparamsbyapiid(@RequestBody Map<String, Object> param) {
        final List<WsapiParams> list = this.wsapiParamsService.getApiParamsbyapiid(param);
        final PageInfo<WsapiParams> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    /**
     * 输入框查询
     */
    @PostMapping("/search")
    public Result search(@RequestBody final Map<String, Object> param) {
        Integer page = Integer.parseInt(param.get("page").toString());
        Integer size = Integer.parseInt(param.get("size").toString());
        PageHelper.startPage(page, size);
        final List<WsapiParams> list = wsapiParamsService.findDicWithName(param);
        final PageInfo<WsapiParams> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
