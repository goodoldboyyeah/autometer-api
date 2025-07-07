package com.zoctan.api.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.entity.*;
import com.zoctan.api.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import tk.mybatis.mapper.entity.Condition;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author SeasonFan
 * @date 2024/12/12
 */
@RestController
@RequestMapping("/registercenterinterface")
public class RegistercenterinterfaceController {
    @Resource
    private RegistercenterinterfaceService registercenterinterfaceService;

    @Resource
    private RegistercentermethodsService registercentermethodsService;
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

    @PostMapping
    public Result add(@RequestBody Registercenterinterface registercenterinterface) {
        registercenterinterfaceService.save(registercenterinterface);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        registercenterinterfaceService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PatchMapping
    public Result update(@RequestBody Registercenterinterface registercenterinterface) {
        registercenterinterfaceService.update(registercenterinterface);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Registercenterinterface registercenterinterface = registercenterinterfaceService.getById(id);
        return ResultGenerator.genOkResult(registercenterinterface);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Registercenterinterface> list = registercenterinterfaceService.listAll();
        PageInfo<Registercenterinterface> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final Registercenterinterface params) {

        registercenterinterfaceService.updateDic(params);
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
        final List<Registercenterinterface> list = registercenterinterfaceService.findDicWithName(param);
        final PageInfo<Registercenterinterface> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/exportfromregister")
    public Result exportfromregister(@RequestBody final Map<String, Object> param) throws InterruptedException {
        Integer page = Integer.parseInt(param.get("page").toString());
        Integer size = Integer.parseInt(param.get("size").toString());
        Long assembleid = Long.parseLong(param.get("assembleid").toString());
        Long projectid = Long.parseLong(param.get("projectid").toString());
        EnviromentAssemble enviromentAssemble = enviromentAssembleService.getBy("id", assembleid);
        if (enviromentAssemble == null) {
            return ResultGenerator.genFailedResult("未找到当前环境的注册中心，请检查在环境组件中是否存在或已被删除！");
        }
        String ConStr = enviromentAssemble.getConnectstr();
        String assembletype = enviromentAssemble.getAssembletype();
        if (assembletype.equals("nacos") || assembletype.equals("zookeeper")) {
            if (assembletype.equals("nacos")) {
                try {
                    NamingService namingService = NacosFactory.createNamingService(ConStr);
                    ListView<String> servicelist = namingService.getServicesOfServer(1, Integer.MAX_VALUE);
                    for (String sn : servicelist.getData()) {
                        List<Instance> instanceList = namingService.getAllInstances(sn);
                        for (Instance ins : instanceList) {
                            String Methods = ins.getMetadata().get("methods");
                            String Interface = ins.getMetadata().get("interface");
                            if (Interface != null) {
                                List<String> methods = Arrays.asList(Methods.split(","));
                                String Version = ins.getMetadata().get("version");
                                String Group = ins.getMetadata().get("group");
                                if (Group == null) {
                                    Group = "DEFAULT_GROUP";
                                }
                                Condition con = new Condition(Registercenterinterface.class);
                                con.createCriteria().andCondition("interfacename = '" + Interface + "'")
                                        .andCondition("projectid = " + projectid)
                                        .andCondition("version = '" + Version + "'")
                                        .andCondition("dgroup = '" + Group + "'");
                                if (registercenterinterfaceService.ifexist(con) == 0) {
                                    Registercenterinterface registercenterinterface = new Registercenterinterface();
                                    registercenterinterface.setInterfacename(Interface);
                                    registercenterinterface.setVersion(Version);
                                    registercenterinterface.setDgroup(Group);
                                    registercenterinterface.setProjectid(projectid);
                                    registercenterinterface.setCreateTime(new Date());
                                    registercenterinterface.setLastmodifyTime(new Date());
                                    registercenterinterfaceService.save(registercenterinterface);
                                    for (String method:methods) {
                                        Registercentermethods registercentermethods=new Registercentermethods();
                                        registercentermethods.setMethodname(method);
                                        registercentermethods.setId(null);
                                        registercentermethods.setInterfaceid(registercenterinterface.getId());
                                        registercentermethods.setCreateTime(new Date());
                                        registercentermethods.setLastmodifyTime(new Date());
                                        registercentermethodsService.save(registercentermethods);
                                    }
                                }else {
                                    Registercenterinterface registercenterinterface=registercenterinterfaceService.listByCondition(con).get(0);
                                    registercenterinterface.setDgroup(Group);
                                    registercenterinterface.setVersion(Version);
                                    registercenterinterface.setLastmodifyTime(new Date());
                                    registercenterinterfaceService.updateDic(registercenterinterface);
                                    long deploymodelid=registercenterinterface.getId();
                                    registercentermethodsService.deletemethods(deploymodelid);
                                    for (String method:methods) {
                                        Registercentermethods registercentermethods=new Registercentermethods();
                                        registercentermethods.setMethodname(method);
                                        registercentermethods.setId(null);
                                        registercentermethods.setInterfaceid(registercenterinterface.getId());
                                        registercentermethods.setCreateTime(new Date());
                                        registercentermethods.setLastmodifyTime(new Date());
                                        registercentermethodsService.save(registercentermethods);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    return ResultGenerator.genFailedResult("连接nacos异常！：" + ex.getMessage());
                }
            }
            if (assembletype.equals("zookeeper")) {
                ZkClient zkClient = null;
                try {
                    ConStr = ConStr.replace("zookeeper://", "");
                    String DUBBO_SERVICE_PATH = "/dubbo";
                    zkClient = new ZkClient(ConStr, 5000);
                    // 检查 Dubbo 服务路径是否存在
                    if (zkClient.exists(DUBBO_SERVICE_PATH)) {
                        // 获取所有服务接口
                        List<String> serviceInterfaces = zkClient.getChildren(DUBBO_SERVICE_PATH);
                        for (String serviceInterface : serviceInterfaces) {
                            // 获取每个服务接口下的所有提供者
                            String servicePath = DUBBO_SERVICE_PATH + "/" + serviceInterface + "/providers";
                            if (zkClient.exists(servicePath)) {
                                List<String> providers = zkClient.getChildren(servicePath);
                                for (String provider : providers) {
                                    String decodedUrl = URLDecoder.decode(provider, StandardCharsets.UTF_8.toString());
                                    // 解析 URL
                                    URL url = new URL(decodedUrl.replace("dubbo","http"));
                                    String query = url.getQuery();
                                    // 解析查询参数
                                    Map<String, String> parameters = parseQuery(query);
                                    String Version = parameters.get("version");
                                    String Group = parameters.get("group");
                                    // 获取方法名
                                    List<String> methods = new ArrayList<>();
                                    String methodsParam = parameters.get("methods");
                                    if (methodsParam != null && !methodsParam.isEmpty()) {
                                        methods = Arrays.asList(methodsParam.split(","));
                                    }
                                    Condition con = new Condition(Registercenterinterface.class);
                                    con.createCriteria().andCondition("interfacename = '" + serviceInterface + "'")
                                            .andCondition("projectid = " + projectid)
                                            .andCondition("version = '" + Version + "'")
                                            .andCondition("dgroup = '" + Group + "'");
                                    if (registercenterinterfaceService.ifexist(con) == 0) {
                                        Registercenterinterface registercenterinterface = new Registercenterinterface();
                                        registercenterinterface.setInterfacename(serviceInterface);
                                        registercenterinterface.setVersion(Version);
                                        registercenterinterface.setDgroup(Group);
                                        registercenterinterface.setProjectid(projectid);
                                        registercenterinterface.setCreateTime(new Date());
                                        registercenterinterface.setLastmodifyTime(new Date());
                                        registercenterinterfaceService.save(registercenterinterface);
                                        for (String method:methods) {
                                            Registercentermethods registercentermethods=new Registercentermethods();
                                            registercentermethods.setMethodname(method);
                                            registercentermethods.setId(null);
                                            registercentermethods.setInterfaceid(registercenterinterface.getId());
                                            registercentermethods.setCreateTime(new Date());
                                            registercentermethods.setLastmodifyTime(new Date());
                                            registercentermethodsService.save(registercentermethods);
                                        }
                                    }else {
                                        Registercenterinterface registercenterinterface=registercenterinterfaceService.listByCondition(con).get(0);
                                        registercenterinterface.setDgroup(Group);
                                        registercenterinterface.setVersion(Version);
                                        registercenterinterface.setLastmodifyTime(new Date());
                                        registercenterinterfaceService.updateDic(registercenterinterface);
                                        long deploymodelid=registercenterinterface.getId();
                                        registercentermethodsService.deletemethods(deploymodelid);
                                        for (String method:methods) {
                                            Registercentermethods registercentermethods=new Registercentermethods();
                                            registercentermethods.setMethodname(method);
                                            registercentermethods.setId(null);
                                            registercentermethods.setInterfaceid(registercenterinterface.getId());
                                            registercentermethods.setCreateTime(new Date());
                                            registercentermethods.setLastmodifyTime(new Date());
                                            registercentermethodsService.save(registercentermethods);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("No providers found for service: " + serviceInterface);
                            }
                        }
                    } else {
                        return ResultGenerator.genFailedResult("No Dubbo services found in ZooKeeper");
                    }
                } catch (Exception ex) {
                    return ResultGenerator.genFailedResult("连接zookeeper异常！：" + ex.getMessage());
                } finally {
//                    zooKeeper.close();
                    // 关闭 ZooKeeper 客户端
                    zkClient.close();
                }
            }
        }

        PageHelper.startPage(page, size);
        final List<DeployunitModel> list = this.deployunitModelService.findDeployModelWithName(param);
        PageInfo<DeployunitModel> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    private  Map<String, String> parseQuery(String query) {
        Map<String, String> parameters = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    parameters.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return parameters;
    }

}
