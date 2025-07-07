package com.zoctan.api.controller;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.*;
import com.zoctan.api.service.AccountRoleService;
import com.zoctan.api.service.ApicasesService;
import com.zoctan.api.service.DubboapiParamsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.service.DubboapiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2024/10/27
 */
@RestController
@RequestMapping("/dubboapi/params")
public class DubboapiParamsController {
    @Resource
    private DubboapiParamsService dubboapiParamsService;

    @Resource
    private DubboapiService dubboapiService;

    @Resource
    private AccountRoleService accountRoleService;

    @Resource
    private ApicasesService apicasesService;

    @PostMapping
    public Result add(@RequestBody DubboapiParams dubboapiParams) {
        dubboapiParamsService.save(dubboapiParams);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/searchbyid")
    public Result searchbyid(@RequestBody Map<String, Object> param) {
        Long apiid= Long.parseLong(param.get("id").toString());
        final List<DubboapiParams> list = this.dubboapiParamsService.getApiParamsbyid(apiid);
        return ResultGenerator.genOkResult(list);
    }

    @PostMapping("/addapiallparams")
    public Result addallparams(@RequestBody List<DubboapiParams> apiParamsList) {
        if (apiParamsList.size() > 0) {
            Long apiid = apiParamsList.get(0).getApiid();
            Dubboapi dubboapi = dubboapiService.getById(apiid);
            Long currentaccountid = apiParamsList.get(0).getCreatorid();
            AccountRole accountRole = accountRoleService.getBy("accountId", currentaccountid);
            if (dubboapi != null) {
                if (currentaccountid.equals(dubboapi.getMid()) || accountRole.getRoleId() == 1) {
                    updateparams(apiParamsList);
                } else {
                    return ResultGenerator.genFailedResult("当前api参数只有api维护人或者管理员可以修改");
                }
            } else {
                return ResultGenerator.genFailedResult("当前api不存在");
            }
        }
        return ResultGenerator.genOkResult();
    }

    private void updateparams(List<DubboapiParams> apiParamsList) {
        for (DubboapiParams apiparam : apiParamsList) {
            Long apiidnew = apiparam.getApiid();
            String KeyName = apiparam.getKeyname();
            String KeyValue = apiparam.getKeydefaultvalue();
            String ParamType = apiparam.getParamstype();
            List<Apicases> apicasesList = apicasesService.getcasebyapiid(apiidnew);
            if (apiparam.getId() == null) {
                if ((!apiparam.getKeyname().isEmpty())  || (!apiparam.getKeydefaultvalue().isEmpty())) {
                    dubboapiParamsService.SaveApiParams(apiparam);
                    //新增所有用例值的参数名，参数值为默认值
//                    if (apicasesList.size() > 0) {
//                        List<ApiCasedata> apiCasedataList = new ArrayList<>();
//                        for (Apicases apicase : apicasesList) {
//                            ApiCasedata apiCasedata = new ApiCasedata();
//                            apiCasedata.setCaseid(apicase.getId());
//                            apiCasedata.setCasename(apicase.getCasename());
//                            apiCasedata.setApiparam(KeyName);
//                            apiCasedata.setApiparamvalue(KeyValue);
//                            apiCasedata.setPropertytype(Protype);
//                            apiCasedata.setParamstype(ParamType);
//                            apiCasedata.setMemo("");
//                            apiCasedata.setMid(apiparam.getCreatorid());
//                            apiCasedataList.add(apiCasedata);
//                        }
//                        if (apiCasedataList.size() > 0) {
//                            apiCasedataService.saveCasedata(apiCasedataList);
//                        }
//                    }
                }
            } else {
                DubboapiParams oldparam = dubboapiParamsService.getById(apiparam.getId());
                String OldParamName = oldparam.getKeyname();
                dubboapiParamsService.updateApiParams(apiparam);
                //更新所有用例值的参数名，（参数值先不刷新成默认值）
//                for (Apicases apicase : apicasesList) {
//                    apiCasedataService.updateparambycaseidandprotypeandapiparam(apicase.getId(), Protype, KeyName, OldParamName, ParamType, EnytypeType);
//                }
            }
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        dubboapiParamsService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody DubboapiParams dubboapiParams) {
        dubboapiParamsService.update(dubboapiParams);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        DubboapiParams dubboapiParams = dubboapiParamsService.getById(id);
        return ResultGenerator.genOkResult(dubboapiParams);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DubboapiParams> list = dubboapiParamsService.listAll();
        PageInfo<DubboapiParams> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final DubboapiParams recipe) {
        dubboapiParamsService.updateDic(recipe);
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
        final List<DubboapiParams> list = dubboapiParamsService.findDicWithName(param);
        final PageInfo<DubboapiParams> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
