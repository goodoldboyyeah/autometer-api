package com.zoctan.api.core.service;


import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
//import org.apache.dubbo.config.bootstrap.DubboBootstrap;
//import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.List;

public class DubboHelp {

    private ApplicationConfig applicationConfig;

    public void InitApplication(String Name) {
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("TestmyProvider"); // 服务提供方名称
        applicationConfig.setQosEnable(false); // 关闭qos服务

    }

    private RegistryConfig registryConfig;

    public void InitRegister(String Url) {
        // 创建注册中心配置
        registryConfig = new RegistryConfig();
        registryConfig.setAddress(Url);
//        registryConfig.setAddress("zookeeper://192.168.3.235:2181");
        registryConfig.setRegister(false); // 是否向注册中心注册服务, false为只订阅, 不注册
    }


    public HashMap<String, HashMap<String,HashMap<String, HashMap<String, GenericService>>>> getIServicegenericService() {
        return IServicegenericService;
    }

    private HashMap<String, HashMap<String,HashMap<String, HashMap<String, GenericService>>>> IServicegenericService=new HashMap<>();

    public void InitIServicegenericService(String Deployunit, String IService, String Version,String Group) throws Exception {
        // 创建服务引用配置, reference封装了与注册中心以及提供者的连接, 是个很重的实例
        try {
            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setRegistry(registryConfig);
            referenceConfig.setApplication(applicationConfig);

            referenceConfig.setInterface(IService); // rpc接口
            referenceConfig.setVersion(Version);
            referenceConfig.setGroup(Group);
            referenceConfig.setGeneric("true"); // 重点: 设置泛化调用

            GenericService genericService = referenceConfig.get();
            if (!IServicegenericService.containsKey(Deployunit)) {
                HashMap<String, HashMap<String,HashMap<String, GenericService>>> IServicenewMap = new HashMap<>();
                HashMap<String, GenericService> GroupnewMap = new HashMap<String, GenericService>();
                GroupnewMap.put(Group, genericService);
                HashMap<String,HashMap<String, GenericService>> VersionnewMap = new HashMap<>();
                VersionnewMap.put(Version, GroupnewMap);
                IServicenewMap.put(IService, VersionnewMap);
                IServicegenericService.put(Deployunit, IServicenewMap);
            } else {
                if (!IServicegenericService.get(Deployunit).containsKey(IService)) {
                    HashMap<String, GenericService> GroupnewMap1 = new HashMap<String, GenericService>();
                    GroupnewMap1.put(Group, genericService);
                    HashMap<String,HashMap<String, GenericService>> VersionnewMap = new HashMap<>();
                    VersionnewMap.put(Version, GroupnewMap1);
                    IServicegenericService.get(Deployunit).put(IService, VersionnewMap);
                } else {
                    if (!IServicegenericService.get(Deployunit).get(IService).containsKey(Version)) {
                        HashMap<String, GenericService> GroupnewMap2 = new HashMap<String, GenericService>();
                        GroupnewMap2.put(Group, genericService);
                        HashMap<String,HashMap<String, GenericService>> VersionnewMap = new HashMap<>();
                        VersionnewMap.put(Version, GroupnewMap2);
                        IServicegenericService.get(Deployunit).get(IService).put(Version, GroupnewMap2);
                    }else
                    {
                        IServicegenericService.get(Deployunit).get(IService).get(Version).put(Group, genericService);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new Exception("初始化dubbo服务接口异常,请检查被测服务是否正常启动");
        }
    }

    public Object CallerIServiceMethod(String Deployunit, String IService, String Version,String Group, String Method, String[] ParamTypeList, Object[] ParamValueList) throws Exception {
        Object result = null;
        try {
            if (IServicegenericService.size() == 0) {
                throw new Exception("初始化dubbo服务接口异常，请检查被测服务是否正常启动");
            }
            GenericService genericService = IServicegenericService.get(Deployunit).get(IService).get(Version).get(Group);
            if (genericService != null) {
                RpcContext.getContext().setAttachment("generic", "gson");
                result = genericService.$invoke(Method, ParamTypeList, ParamValueList);
            }else
            {
                throw new Exception("调用dubbo服务接口异常，请检查被测服务是否正常启动");
            }
        } catch (Exception e) {
            throw new Exception("调用dubbo服务接口异常：" + e.getMessage());
        }
        return result;
    }


}

