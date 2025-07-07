package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.DubboapiCasedata;
import com.zoctan.api.entity.WsapiCasedata;
import com.zoctan.api.service.WsapiCasedataService;
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
@RequestMapping("/wsapi/casedata")
public class WsapiCasedataController {
    @Resource
    private WsapiCasedataService wsapiCasedataService;

    @PostMapping
    public Result add(@RequestBody WsapiCasedata wsapiCasedata) {
        wsapiCasedataService.save(wsapiCasedata);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        wsapiCasedataService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody WsapiCasedata wsapiCasedata) {
        wsapiCasedataService.update(wsapiCasedata);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        WsapiCasedata wsapiCasedata = wsapiCasedataService.getById(id);
        return ResultGenerator.genOkResult(wsapiCasedata);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WsapiCasedata> list = wsapiCasedataService.listAll();
        PageInfo<WsapiCasedata> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final WsapiCasedata params) {

        wsapiCasedataService.updateDic(params);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/casevalue")
    public Result casevalue(@RequestBody final Map<String, Object> param) {
        final List<WsapiCasedata> list = this.wsapiCasedataService.getcasedatabycaseid(param);
        final PageInfo<WsapiCasedata> pageInfo = new PageInfo<>(list);
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
        final List<WsapiCasedata> list = wsapiCasedataService.findDicWithName(param);
        final PageInfo<WsapiCasedata> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
