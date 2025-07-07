package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.AccountRole;
import com.zoctan.api.entity.ApiCasedata;
import com.zoctan.api.entity.Apicases;
import com.zoctan.api.entity.DubboapiCasedata;
import com.zoctan.api.service.AccountRoleService;
import com.zoctan.api.service.ApicasesService;
import com.zoctan.api.service.DubboapiCasedataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/30
 */
@RestController
@RequestMapping("/dubboapi/casedata")
public class DubboapiCasedataController {
    @Resource
    private DubboapiCasedataService dubboapiCasedataService;

    @Resource
    private ApicasesService apicasesService;

    @Resource
    private AccountRoleService accountRoleService;



    @PostMapping
    public Result add(@RequestBody DubboapiCasedata dubboapiCasedata) {
        dubboapiCasedataService.save(dubboapiCasedata);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dubboapiCasedataService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody DubboapiCasedata dubboapiCasedata) {
        dubboapiCasedataService.update(dubboapiCasedata);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        DubboapiCasedata dubboapiCasedata = dubboapiCasedataService.getById(id);
        return ResultGenerator.genOkResult(dubboapiCasedata);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DubboapiCasedata> list = dubboapiCasedataService.listAll();
        PageInfo<DubboapiCasedata> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final DubboapiCasedata recipe) {
        dubboapiCasedataService.updateDic(recipe);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/casevalue")
    public Result casevalue(@RequestBody final Map<String, Object> param) {
        final List<DubboapiCasedata> list = this.dubboapiCasedataService.getcasedatabycaseid(param);
        final PageInfo<DubboapiCasedata> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/updatepropertydata")
    public Result updatepropertydata(@RequestBody List<DubboapiCasedata> apiCasedataList) {
        for (DubboapiCasedata apiCasedata : apiCasedataList) {
            Long apicaseidid = apiCasedataList.get(0).getCaseid();
            Apicases apicases = apicasesService.getById(apicaseidid);
            Long currentaccountid = apiCasedataList.get(0).getMid();
            AccountRole accountRole = accountRoleService.getBy("accountId", currentaccountid);
            if (apicases != null) {
                if (currentaccountid.equals(apicases.getMid()) || accountRole.getRoleId() == 1) {
                    dubboapiCasedataService.update(apiCasedata);
                } else {
                    return ResultGenerator.genFailedResult("当前用例数据只有维护人或者管理员可以修改");
                }
            } else {
                return ResultGenerator.genFailedResult("当前用例不存在");
            }
        }
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
        final List<DubboapiCasedata> list = dubboapiCasedataService.findDicWithName(param);
        final PageInfo<DubboapiCasedata> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
