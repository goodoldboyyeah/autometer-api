package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.DispatchDubboapicasedata;
import com.zoctan.api.service.DispatchDubboapicasedataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/11/04
 */
@RestController
@RequestMapping("/dispatch/dubboapicasedata")
public class DispatchDubboapicasedataController {
    @Resource
    private DispatchDubboapicasedataService dispatchDubboapicasedataService;

    @PostMapping
    public Result add(@RequestBody DispatchDubboapicasedata dispatchDubboapicasedata) {
        dispatchDubboapicasedataService.save(dispatchDubboapicasedata);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dispatchDubboapicasedataService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody DispatchDubboapicasedata dispatchDubboapicasedata) {
        dispatchDubboapicasedataService.update(dispatchDubboapicasedata);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        DispatchDubboapicasedata dispatchDubboapicasedata = dispatchDubboapicasedataService.getById(id);
        return ResultGenerator.genOkResult(dispatchDubboapicasedata);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DispatchDubboapicasedata> list = dispatchDubboapicasedataService.listAll();
        PageInfo<DispatchDubboapicasedata> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final DispatchDubboapicasedata recipe) {
        dispatchDubboapicasedataService.updateDic(recipe);
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
        final List<DispatchDubboapicasedata> list = dispatchDubboapicasedataService.findDicWithName(param);
        final PageInfo<DispatchDubboapicasedata> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
