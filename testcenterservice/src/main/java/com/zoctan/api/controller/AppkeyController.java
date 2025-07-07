package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.Appkey;
import com.zoctan.api.service.AppkeyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/09/07
 */
@RestController
@RequestMapping("/appkey")
public class AppkeyController {
    @Resource
    private AppkeyService appkeyService;

    @PostMapping
    public Result add(@RequestBody Appkey appkey) {
        if (appkeyService.listAll().size() > 0) {
            return ResultGenerator.genFailedResult("已经存在秘钥，不能重复添加");
        } else {
            appkeyService.save(appkey);
            return ResultGenerator.genOkResult();
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        appkeyService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody Appkey appkey) {
        appkeyService.update(appkey);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Appkey appkey = appkeyService.getById(id);
        return ResultGenerator.genOkResult(appkey);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Appkey> list = appkeyService.listAll();
        PageInfo<Appkey> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final Appkey appkey) {
        appkeyService.updateDic(appkey);
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
        final List<Appkey> list = appkeyService.findDicWithName(param);
        final PageInfo<Appkey> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
