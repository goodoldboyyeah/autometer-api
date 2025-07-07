package com.zoctan.api.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.dto.RegistercenterinterfaceDto;
import com.zoctan.api.entity.*;
import com.zoctan.api.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author SeasonFan
 * @date 2022/11/24
 */
@RestController
@RequestMapping("/deployunit/model")
public class DeployunitModelController {
    @Resource
    private DeployunitModelService deployunitModelService;

    @Resource
    private DeployunitService deployunitService;

    @Resource
    private ApiService apiService;

    @Resource
    private DubboapiService dubboapiService;

    @Resource
    private ApicasesService apicasesService;

    @Resource
    private EnviromentAssembleService enviromentAssembleService;

    @Resource
    private DubbomodelmethodsService dubbomodelmethodsService;


    @Resource
    private RegistercentermethodsService registercentermethodsService;




    @PostMapping
    public Result add(@RequestBody DeployunitModel deployunitModel) {

        Condition con = new Condition(DeployunitModel.class);
        con.createCriteria().andCondition("deployunitid = " + deployunitModel.getDeployunitid())
                .andCondition("projectid = " + deployunitModel.getProjectid())
                .andCondition("modelname = '" + deployunitModel.getModelname().replace("'", "''") + "'");
        if (deployunitModelService.ifexist(con) > 0) {
            return ResultGenerator.genFailedResult("此微服务模块已经存在");
        } else {
            deployunitModelService.save(deployunitModel);
            return ResultGenerator.genOkResult();
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        DeployunitModel deployunitModel = deployunitModelService.getById(id);
        if (deployunitModel != null) {
            long deployunitid = deployunitModel.getDeployunitid();
            Deployunit deployunit = deployunitService.getById(deployunitid);
            String servicetype = deployunit.getServicetype();
            if (servicetype.equals("Http服务")) {
                Condition con = new Condition(Api.class);
                con.createCriteria().andCondition("modelid = " + id);
                List<Api> apiList = apiService.listByCondition(con);
                if (apiList.size() > 0) {
                    return ResultGenerator.genFailedResult("存在正在使用此的api，无法删除");
                }
            }
            if (servicetype.equals("Dubbo服务")) {
                Condition con = new Condition(Dubboapi.class);
                con.createCriteria().andCondition("modelid = " + id);
                List<Dubboapi> apiList = dubboapiService.listByCondition(con);
                if (apiList.size() > 0) {
                    return ResultGenerator.genFailedResult("存在正在使用此的api，无法删除");
                }
            }
            deployunitModelService.deleteById(id);
            dubbomodelmethodsService.deletemethods(id);
        } else {
            return ResultGenerator.genFailedResult("未找到此微服务模块");
        }
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody DeployunitModel deployunitModel) {
        deployunitModelService.update(deployunitModel);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        DeployunitModel deployunitModel = deployunitModelService.getById(id);
        return ResultGenerator.genOkResult(deployunitModel);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DeployunitModel> list = deployunitModelService.listAll();
        PageInfo<DeployunitModel> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    /**
     * 更新自己的资料
     */
    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final DeployunitModel deployunitModel) {
        Condition con = new Condition(DeployunitModel.class);
        con.createCriteria().andCondition("deployunitid = " + deployunitModel.getDeployunitid())
                .andCondition("modelname = '" + deployunitModel.getModelname().replace("'", "''") + "'")
                .andCondition("id <> " + deployunitModel.getId());
        if (deployunitModelService.ifexist(con) > 0) {
            return ResultGenerator.genFailedResult("此微服务模块已经存在");
        } else {
            DeployunitModel existdeployunitModel = deployunitModelService.getById(deployunitModel.getId());
            long deployunitid = existdeployunitModel.getDeployunitid();
            Deployunit deployunit = deployunitService.getById(deployunitid);
            String servicetype = deployunit.getServicetype();
            if (servicetype.equals("Http服务")) {
                apiService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname());
            }
            if (servicetype.equals("Dubbo服务")) {
                dubboapiService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname(),deployunitModel.getVersion(),deployunitModel.getDgroup());
            }
            apicasesService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname());
            this.deployunitModelService.updateDeploy(deployunitModel);
            return ResultGenerator.genOkResult();
        }
    }

    /**
     * 输入框查询
     */
    @PostMapping("/search")
    public Result search(@RequestBody final Map<String, Object> param) {
        Integer page = Integer.parseInt(param.get("page").toString());
        Integer size = Integer.parseInt(param.get("size").toString());
        PageHelper.startPage(page, size);
        final List<DeployunitModel> list = this.deployunitModelService.findDeployModelWithName(param);
        PageInfo<DeployunitModel> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

//    @PostMapping("/exportfromregister")
//    public Result exportfromregister(@RequestBody final Map<String, Object> param) throws InterruptedException {
//        Integer page = Integer.parseInt(param.get("page").toString());
//        Integer size = Integer.parseInt(param.get("size").toString());
//        Long assembleid = Long.parseLong(param.get("assembleid").toString());
//        Long deployunitid = Long.parseLong(param.get("deployunitid").toString());
//        EnviromentAssemble enviromentAssemble = enviromentAssembleService.getBy("id", assembleid);
//        if (enviromentAssemble == null) {
//            return ResultGenerator.genFailedResult("未找到当前环境的注册中心，请检查在环境组件中是否存在或已被删除！");
//        }
//        String ConStr = enviromentAssemble.getConnectstr();
//        String assembletype = enviromentAssemble.getAssembletype();
//        if (assembletype.equals("nacos") || assembletype.equals("zookeeper")) {
//            if (assembletype.equals("nacos")) {
//                try {
//                    NamingService namingService = NacosFactory.createNamingService(ConStr);
//                    ListView<String> servicelist = namingService.getServicesOfServer(1, Integer.MAX_VALUE);
//                    for (String sn : servicelist.getData()) {
//                        List<Instance> instanceList = namingService.getAllInstances(sn);
//                        for (Instance ins : instanceList) {
////                            String address = instance.getIp() + ":" + instance.getPort();
////                            Map<String, String> metadata = instance.getMetadata();
////
////                            // 构建 URL 对象
////                            URL url = URL.valueOf("dubbo://" + address + "/" + SERVICE_NAME)
////                                    .addParameters(metadata)
////                                    .addParameter("application", "dubbo-consumer")
////                                    .addParameter("side", "consumer")
////                                    .addParameter("timestamp", String.valueOf(System.currentTimeMillis()));
////
////                            // 打印 Provider 信息
////                            System.out.println("Provider Address: " + address);
////                            System.out.println("Metadata: " + metadata);
////                            System.out.println("URL: " + url.toFullString());
////
////                            // 获取方法信息
////                            List<String> methods = url.getParameter("methods", "").isEmpty() ? List.of() : List.of(url.getParameter("methods").split(","));
////                            for (String method : methods) {
////                                System.out.println("Method: " + method);
////
//                            String Methods = ins.getMetadata().get("methods");
//                            String Interface = ins.getMetadata().get("interface");
//                            if (Interface != null) {
//                                List<String> methods = Arrays.asList(Methods.split(","));
//                                String Version = ins.getMetadata().get("version");
//                                String Group = ins.getMetadata().get("group");
//                                if (Group == null) {
//                                    Group = "DEFAULT_GROUP";
//                                }
//                                Condition con = new Condition(DeployunitModel.class);
//                                con.createCriteria().andCondition("deployunitid = " + deployunitid)
//                                        .andCondition("modelname = '" + Interface + "'")
//                                        .andCondition("version = '" + Version + "'")
//                                        .andCondition("dgroup = '" + Group + "'");
//                                if (deployunitModelService.ifexist(con) == 0) {
//                                    DeployunitModel deployunitModel = new DeployunitModel();
//                                    deployunitModel.setModelname(Interface);
//                                    deployunitModel.setVersion(Version);
//                                    deployunitModel.setDgroup(Group);
//                                    deployunitModel.setDeployunitid(deployunitid);
//                                    deployunitModel.setCreateTime(new Date());
//                                    deployunitModel.setLastmodifyTime(new Date());
//                                    deployunitModel.setMemo("从注册中心导入");
//                                    deployunitModelService.save(deployunitModel);
//                                    for (String method:methods) {
//                                        Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
//                                        dubbomodelmethods.setMethodname(method);
//                                        dubbomodelmethods.setModelid(null);
//                                        dubbomodelmethods.setModelid(deployunitModel.getId());
//                                        dubbomodelmethods.setCreateTime(new Date());
//                                        dubbomodelmethods.setLastmodifyTime(new Date());
//                                        dubbomodelmethodsService.save(dubbomodelmethods);
//                                    }
//                                }else {
//                                    DeployunitModel deployunitModel=deployunitModelService.listByCondition(con).get(0);
//                                    deployunitModel.setDgroup(Group);
//                                    deployunitModel.setVersion(Version);
//                                    deployunitModel.setLastmodifyTime(new Date());
//                                    deployunitModelService.updateDeploy(deployunitModel);
//                                    dubboapiService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname(),deployunitModel.getVersion(),deployunitModel.getDgroup());
//                                    apicasesService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname());
//                                    long deploymodelid=deployunitModel.getId();
//                                    dubbomodelmethodsService.deletemethods(deploymodelid);
//                                    for (String method:methods) {
//                                        Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
//                                        dubbomodelmethods.setMethodname(method);
//                                        dubbomodelmethods.setModelid(null);
//                                        dubbomodelmethods.setModelid(deployunitModel.getId());
//                                        dubbomodelmethods.setCreateTime(new Date());
//                                        dubbomodelmethods.setLastmodifyTime(new Date());
//                                        dubbomodelmethodsService.save(dubbomodelmethods);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception ex) {
//                    return ResultGenerator.genFailedResult("连接nacos异常！：" + ex.getMessage());
//                }
//            }
//            if (assembletype.equals("zookeeper")) {
//                ZkClient zkClient = null;
//                try {
//                    ConStr = ConStr.replace("zookeeper://", "");
//                    String DUBBO_SERVICE_PATH = "/dubbo";
//                    zkClient = new ZkClient(ConStr, 5000);
//                    // 检查 Dubbo 服务路径是否存在
//                    if (zkClient.exists(DUBBO_SERVICE_PATH)) {
//                        // 获取所有服务接口
//                        List<String> serviceInterfaces = zkClient.getChildren(DUBBO_SERVICE_PATH);
//                        for (String serviceInterface : serviceInterfaces) {
//                            // 获取每个服务接口下的所有提供者
//                            String servicePath = DUBBO_SERVICE_PATH + "/" + serviceInterface + "/providers";
//                            if (zkClient.exists(servicePath)) {
//                                List<String> providers = zkClient.getChildren(servicePath);
//                                for (String provider : providers) {
//                                    String decodedUrl = URLDecoder.decode(provider, StandardCharsets.UTF_8.toString());
//                                    // 解析 URL
//                                    URL url = new URL(decodedUrl.replace("dubbo","http"));
//                                    String query = url.getQuery();
//                                    // 解析查询参数
//                                    Map<String, String> parameters = parseQuery(query);
//                                    String Version = parameters.get("version");
//                                    String Group = parameters.get("group");
//                                    // 获取方法名
//                                    List<String> methods = new ArrayList<>();
//                                    String methodsParam = parameters.get("methods");
//                                    if (methodsParam != null && !methodsParam.isEmpty()) {
//                                        methods = Arrays.asList(methodsParam.split(","));
//                                    }
//                                    Condition con = new Condition(DeployunitModel.class);
//                                    con.createCriteria().andCondition("deployunitid = " + deployunitid)
//                                            .andCondition("modelname = '" + serviceInterface + "'");
//                                    if (deployunitModelService.ifexist(con) == 0) {
//                                        DeployunitModel deployunitModel = new DeployunitModel();
//                                        deployunitModel.setModelname(serviceInterface);
//                                        deployunitModel.setVersion(Version);
//                                        deployunitModel.setDgroup(Group);
//                                        deployunitModel.setDeployunitid(deployunitid);
//                                        deployunitModel.setCreateTime(new Date());
//                                        deployunitModel.setLastmodifyTime(new Date());
//                                        deployunitModel.setMemo("从注册中心导入");
//                                        deployunitModelService.save(deployunitModel);
//                                        for (String method:methods) {
//                                            Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
//                                            dubbomodelmethods.setMethodname(method);
//                                            dubbomodelmethods.setModelid(null);
//                                            dubbomodelmethods.setModelid(deployunitModel.getId());
//                                            dubbomodelmethods.setCreateTime(new Date());
//                                            dubbomodelmethods.setLastmodifyTime(new Date());
//                                            dubbomodelmethodsService.save(dubbomodelmethods);
//                                        }
//                                    }else {
//                                        DeployunitModel deployunitModel=deployunitModelService.listByCondition(con).get(0);
//                                        deployunitModel.setDgroup(Group);
//                                        deployunitModel.setVersion(Version);
//                                        deployunitModel.setLastmodifyTime(new Date());
//                                        deployunitModelService.updateDeploy(deployunitModel);
//                                        dubboapiService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname(),deployunitModel.getVersion(),deployunitModel.getDgroup());
//                                        apicasesService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname());
//                                        long deploymodelid=deployunitModel.getId();
//                                        dubbomodelmethodsService.deletemethods(deploymodelid);
//                                        for (String method:methods) {
//                                            Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
//                                            dubbomodelmethods.setMethodname(method);
//                                            dubbomodelmethods.setModelid(null);
//                                            dubbomodelmethods.setModelid(deployunitModel.getId());
//                                            dubbomodelmethods.setCreateTime(new Date());
//                                            dubbomodelmethods.setLastmodifyTime(new Date());
//                                            dubbomodelmethodsService.save(dubbomodelmethods);
//                                        }
//                                    }
//                                }
//                            } else {
//                                System.out.println("No providers found for service: " + serviceInterface);
//                            }
//                        }
//                    } else {
//                        System.out.println("No Dubbo services found in ZooKeeper");
//                    }
//                } catch (Exception ex) {
//                    return ResultGenerator.genFailedResult("连接zookeeper异常！：" + ex.getMessage());
//                } finally {
////                    zooKeeper.close();
//                    // 关闭 ZooKeeper 客户端
//                    zkClient.close();
//                }
//            }
//        }
//
//        PageHelper.startPage(page, size);
//        final List<DeployunitModel> list = this.deployunitModelService.findDeployModelWithName(param);
//        PageInfo<DeployunitModel> pageInfo = PageInfo.of(list);
//        return ResultGenerator.genOkResult(pageInfo);
//    }
//
//    private  Map<String, String> parseQuery(String query) {
//        Map<String, String> parameters = new HashMap<>();
//        if (query != null && !query.isEmpty()) {
//            String[] pairs = query.split("&");
//            for (String pair : pairs) {
//                String[] keyValue = pair.split("=");
//                if (keyValue.length == 2) {
//                    parameters.put(keyValue[0], keyValue[1]);
//                }
//            }
//        }
//        return parameters;
//    }


    @PostMapping("/addinterfacemethods")
    public Result addinterfacemethods(@RequestBody final List<RegistercenterinterfaceDto> testsceneTestcaseList) {
        for (RegistercenterinterfaceDto registercenterinterfaceDto:testsceneTestcaseList) {
            long deployunitid=registercenterinterfaceDto.getDeployunitid();
            long interfaceid=registercenterinterfaceDto.getId();
            long projectid=registercenterinterfaceDto.getProjectid();
            String servicetype=registercenterinterfaceDto.getServicetype();
            String Version=registercenterinterfaceDto.getVersion();
            String Group=registercenterinterfaceDto.getDgroup();
            String Interface=registercenterinterfaceDto.getInterfacename();
            Condition con = new Condition(DeployunitModel.class);
            con.createCriteria().andCondition("deployunitid = " + deployunitid)
                    .andCondition("projectid = " + projectid)
                    .andCondition("modelname = '" + Interface + "'")
                    .andCondition("version = '" + Version + "'")
                    .andCondition("dgroup = '" + Group + "'");
            if (deployunitModelService.ifexist(con) == 0) {
                DeployunitModel deployunitModel = new DeployunitModel();
                deployunitModel.setModelname(Interface);
                deployunitModel.setVersion(Version);
                deployunitModel.setDgroup(Group);
                deployunitModel.setDeployunitid(deployunitid);
                deployunitModel.setProjectid(projectid);
                deployunitModel.setServicetype(servicetype);
                deployunitModel.setCreateTime(new Date());
                deployunitModel.setLastmodifyTime(new Date());
                deployunitModel.setMemo("从注册中心导入");
                deployunitModelService.save(deployunitModel);
                Condition methodcon = new Condition(Registercentermethods.class);
                methodcon.createCriteria().andCondition("interfaceid = " + interfaceid);
                List<Registercentermethods>registercentermethodsList= registercentermethodsService.listByCondition(methodcon);
                for (Registercentermethods method:registercentermethodsList) {
                    Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
                    dubbomodelmethods.setMethodname(method.getMethodname());
                    dubbomodelmethods.setModelid(null);
                    dubbomodelmethods.setModelid(deployunitModel.getId());
                    dubbomodelmethods.setCreateTime(new Date());
                    dubbomodelmethods.setLastmodifyTime(new Date());
                    dubbomodelmethodsService.save(dubbomodelmethods);
                }
            }else {
                DeployunitModel deployunitModel=deployunitModelService.listByCondition(con).get(0);
                deployunitModel.setDgroup(Group);
                deployunitModel.setVersion(Version);
                deployunitModel.setLastmodifyTime(new Date());
                deployunitModelService.updateDeploy(deployunitModel);
                dubboapiService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname(),deployunitModel.getVersion(),deployunitModel.getDgroup());
                apicasesService.updateApibymodelid(deployunitModel.getId(), deployunitModel.getModelname());
                long deploymodelid=deployunitModel.getId();
                dubbomodelmethodsService.deletemethods(deploymodelid);
                Condition methodcon = new Condition(Registercentermethods.class);
                methodcon.createCriteria().andCondition("interfaceid = " + interfaceid);
                List<Registercentermethods>registercentermethodsList= registercentermethodsService.listByCondition(methodcon);
                for (Registercentermethods method:registercentermethodsList) {
                    Dubbomodelmethods dubbomodelmethods=new Dubbomodelmethods();
                    dubbomodelmethods.setMethodname(method.getMethodname());
                    dubbomodelmethods.setModelid(null);
                    dubbomodelmethods.setModelid(deployunitModel.getId());
                    dubbomodelmethods.setCreateTime(new Date());
                    dubbomodelmethods.setLastmodifyTime(new Date());
                    dubbomodelmethodsService.save(dubbomodelmethods);
                }
            }
        }
        return ResultGenerator.genOkResult();
    }
}
