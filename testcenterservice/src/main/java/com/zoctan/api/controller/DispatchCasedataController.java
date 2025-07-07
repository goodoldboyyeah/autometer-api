package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.DispatchCasedata;
import com.zoctan.api.service.DispatchCasedataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/08/23
 */
@RestController
@RequestMapping("/dispatch/casedata")
public class DispatchCasedataController {
    @Resource
    private DispatchCasedataService dispatchCasedataService;

    @PostMapping
    public Result add(@RequestBody DispatchCasedata dispatchCasedata) {
        dispatchCasedataService.save(dispatchCasedata);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dispatchCasedataService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody DispatchCasedata dispatchCasedata) {
        dispatchCasedataService.update(dispatchCasedata);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        DispatchCasedata dispatchCasedata = dispatchCasedataService.getById(id);
        return ResultGenerator.genOkResult(dispatchCasedata);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DispatchCasedata> list = dispatchCasedataService.listAll();
        PageInfo<DispatchCasedata> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final DispatchCasedata recipe) {
        dispatchCasedataService.updateDic(recipe);
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
        final List<DispatchCasedata> list = dispatchCasedataService.findDicWithName(param);
        final PageInfo<DispatchCasedata> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
