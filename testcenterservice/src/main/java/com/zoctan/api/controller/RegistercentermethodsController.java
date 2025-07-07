package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.Registercentermethods;
import com.zoctan.api.service.RegistercentermethodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
* @author SeasonFan
* @date 2024/12/12
*/
@RestController
@RequestMapping("/registercentermethods")
public class RegistercentermethodsController {
@Resource
private RegistercentermethodsService registercentermethodsService;

@PostMapping
public Result add(@RequestBody Registercentermethods registercentermethods) {
registercentermethodsService.save(registercentermethods);
return ResultGenerator.genOkResult();
}

@DeleteMapping("/{id}")
public Result delete(@PathVariable Long id) {
registercentermethodsService.deleteById(id);
return ResultGenerator.genOkResult();
}

@PatchMapping
public Result update(@RequestBody Registercentermethods registercentermethods) {
registercentermethodsService.update(registercentermethods);
return ResultGenerator.genOkResult();
}

@GetMapping("/{id}")
public Result detail(@PathVariable Long id) {
Registercentermethods registercentermethods = registercentermethodsService.getById(id);
return ResultGenerator.genOkResult(registercentermethods);
}

@GetMapping
public Result list(@RequestParam(defaultValue = "0") Integer page,
@RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<Registercentermethods> list = registercentermethodsService.listAll();
PageInfo<Registercentermethods> pageInfo = PageInfo.of(list);
return ResultGenerator.genOkResult(pageInfo);
}

@PutMapping("/detail")
public Result updateDeploy(@RequestBody final Registercentermethods params) {

registercentermethodsService.updateDic(params);
return ResultGenerator.genOkResult();
}
/**
* 输入框查询
*/
@PostMapping("/search")
public Result search(@RequestBody final Map<String, Object> param) {
Integer page= Integer.parseInt(param.get("page").toString());
Integer size= Integer.parseInt(param.get("size").toString());
PageHelper.startPage(page, size);
final List<Registercentermethods> list = registercentermethodsService.findDicWithName(param);
final PageInfo<Registercentermethods> pageInfo = new PageInfo<>(list);
return ResultGenerator.genOkResult(pageInfo);
}
}
