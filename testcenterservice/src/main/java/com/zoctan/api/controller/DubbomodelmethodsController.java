package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.Dubbomodelmethods;
import com.zoctan.api.service.DubbomodelmethodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/12/10
 */
@RestController
@RequestMapping("/dubbomodelmethods")
public class DubbomodelmethodsController {
    @Resource
    private DubbomodelmethodsService dubbomodelmethodsService;

    @PostMapping
    public Result add(@RequestBody Dubbomodelmethods dubbomodelmethods) {
        dubbomodelmethodsService.save(dubbomodelmethods);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dubbomodelmethodsService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody Dubbomodelmethods dubbomodelmethods) {
        dubbomodelmethodsService.update(dubbomodelmethods);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Dubbomodelmethods dubbomodelmethods = dubbomodelmethodsService.getById(id);
        return ResultGenerator.genOkResult(dubbomodelmethods);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Dubbomodelmethods> list = dubbomodelmethodsService.listAll();
        PageInfo<Dubbomodelmethods> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final Dubbomodelmethods params) {

        dubbomodelmethodsService.updateDic(params);
        return ResultGenerator.genOkResult();
    }

    /**
     * 输入框查询
     */
    @PostMapping("/search")
    public Result search(@RequestBody final Map<String, Object> param) {
        final List<Dubbomodelmethods> list = dubbomodelmethodsService.findDicWithName(param);
        return ResultGenerator.genOkResult(list);
    }
}
