package com.zoctan.api.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.core.service.DubboHelp;
import com.zoctan.api.dto.AssembleDeploy;
import com.zoctan.api.entity.Enviroment;
import com.zoctan.api.entity.EnviromentAssemble;
import com.zoctan.api.entity.Macdepunit;
import com.zoctan.api.entity.Machine;
import com.zoctan.api.service.EnviromentAssembleService;
import com.zoctan.api.service.MacdepunitService;
import com.zoctan.api.service.MachineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SeasonFan
 * @date 2020/11/06
 */
@Slf4j
@RestController
@RequestMapping("/enviroment_assemble")
public class EnviromentAssembleController {
    @Resource
    private EnviromentAssembleService enviromentAssembleService;
    @Resource
    private MachineService machineService;
    @Resource
    private MacdepunitService macdepunitService;


    @PostMapping
    public Result add(@RequestBody EnviromentAssemble enviromentAssemble) {
        Condition con = new Condition(EnviromentAssemble.class);
        con.createCriteria().andCondition("projectid = " + enviromentAssemble.getProjectid())
                .andCondition("assembletype = '" + enviromentAssemble.getAssembletype() + "'")
                .andCondition("assemblename = '" + enviromentAssemble.getAssemblename().replace("'", "''") + "'");
        if (enviromentAssembleService.ifexist(con) > 0) {
            return ResultGenerator.genFailedResult("已存在相同的环境组件");
        } else {
            enviromentAssembleService.save(enviromentAssemble);
            return ResultGenerator.genOkResult();
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        List<Macdepunit> macdepunitList = macdepunitService.findassemblebyassid(id);
        if (macdepunitList.size() > 0) {
            return ResultGenerator.genFailedResult("当前组件在环境部署中还在使用，无法删除！");
        } else {
            enviromentAssembleService.deleteById(id);
            return ResultGenerator.genOkResult();
        }
    }

    /**
     * 更新自己的资料
     */
    @PutMapping("/detail")
    public Result updateDeploy(@RequestBody final EnviromentAssemble enviromentAssemble) {
        Condition con = new Condition(EnviromentAssemble.class);
        con.createCriteria().andCondition("projectid = " + enviromentAssemble.getProjectid())
                .andCondition("assembletype = '" + enviromentAssemble.getAssembletype() + "'")
                .andCondition("assemblename = '" + enviromentAssemble.getAssemblename().replace("'", "''") + "'")
                .andCondition("id <> " + enviromentAssemble.getId());
        if (enviromentAssembleService.ifexist(con) > 0) {
            return ResultGenerator.genFailedResult("环境组件已经存在");
        } else {

            this.enviromentAssembleService.updateenviromentassemble(enviromentAssemble);
            return ResultGenerator.genOkResult();
        }
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        EnviromentAssemble enviromentAssemble = enviromentAssembleService.getById(id);
        return ResultGenerator.genOkResult(enviromentAssemble);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnviromentAssemble> list = enviromentAssembleService.listAll();
        PageInfo<EnviromentAssemble> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }


    @GetMapping("/getassemblename")
    public Result listall(@RequestParam long projectid) {
        Condition con = new Condition(EnviromentAssemble.class);
        con.createCriteria().andCondition("projectid = " + projectid);
        List<EnviromentAssemble> list = enviromentAssembleService.listByCondition(con);
//        List<EnviromentAssemble> list = enviromentAssembleService.listAll();
        return ResultGenerator.genOkResult(list);
    }

    @PostMapping("/getregister")
    public Result getregister(@RequestBody final Map<String, Object> param) {
        Integer projectid = Integer.parseInt(param.get("projectid").toString());
        Integer envid = Integer.parseInt(param.get("envid").toString());
        Condition con = new Condition(EnviromentAssemble.class);
        con.createCriteria().andCondition("projectid = " + projectid);
        List<EnviromentAssemble> list = enviromentAssembleService.listByCondition(con);
        List<EnviromentAssemble> newlist=new ArrayList<>();
        for (EnviromentAssemble enviromentAssemble : list) {
            if(enviromentAssemble.getAssembletype().equals("nacos")||enviromentAssemble.getAssembletype().equals("zookeeper"))
            {
                newlist.add(enviromentAssemble);
            }
        }
        Map<String, Object> mcparam = new HashMap<>();
        mcparam.put("envid", envid);
        mcparam.put("assembletype", "组件");
        List<AssembleDeploy> macdepunitList = macdepunitService.findMacAndAssembleWithEnv(mcparam);
        List<EnviromentAssemble> resultlist=new ArrayList<>();

        for (AssembleDeploy macdepunit : macdepunitList) {
            for (EnviromentAssemble enviromentAssemble : newlist) {
                if (enviromentAssemble.getId().equals(macdepunit.getAssembleid())) {
                    resultlist.add(enviromentAssemble);                }
            }
        }
        return ResultGenerator.genOkResult(resultlist);
    }

    /**
     * 输入框查询
     */
    @PostMapping("/search")
    public Result search(@RequestBody final Map<String, Object> param) {
        Integer page = Integer.parseInt(param.get("page").toString());
        Integer size = Integer.parseInt(param.get("size").toString());
        PageHelper.startPage(page, size);
        final List<EnviromentAssemble> list = this.enviromentAssembleService.findassembleWithName(param);
        final PageInfo<EnviromentAssemble> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/runtest")
    public Result runtest(@RequestBody final Map<String, Object> param) throws SQLException, InterruptedException {
        Long machineid = Long.parseLong(param.get("machineid").toString());
        String machinename = param.get("machinename").toString();
        String visittype = param.get("visittype").toString();
        String assembletype = param.get("assembletype").toString();
        String ConStr = param.get("constr").toString();
        if (assembletype.equals("nacos")||assembletype.equals("zookeeper")) {
            if(assembletype.equals("nacos")) {
                try {
                    NamingService namingService = NacosFactory.createNamingService(ConStr);
                    ListView<String> servicelist= namingService.getServicesOfServer(1,Integer.MAX_VALUE);
//                    for (String sn:servicelist.getData()){
//                        List<Instance> instanceList = namingService.getAllInstances(sn);
//                        for (Instance ins:instanceList){
//                            ins.getServiceName();
//                        }
//                    }
                }
                catch (Exception ex) {
                    return ResultGenerator.genFailedResult("连接nacos异常！："+ex.getMessage());
                }
            }
            if(assembletype.equals("zookeeper")) {
                ZooKeeper zooKeeper =null;
                try {
                    ConStr = ConStr.replace("zookeeper://","");
                    zooKeeper = new ZooKeeper(ConStr, 3000, event -> {
                    });
                    List<String> zklist= zooKeeper.getChildren("/",true);
//                    for (String zk:zklist){
//                        System.out.println("zookeeper节点："+zk);
//                    }
                }
                catch (Exception ex) {
                    return ResultGenerator.genFailedResult("连接zookeeper异常！："+ex.getMessage());
                }
                finally {
                    zooKeeper.close();
                }
            }
        }else
        {
            String[] ConnetcArray = ConStr.split(",");
            if (ConnetcArray.length < 4) {
                return ResultGenerator.genFailedResult("连接字格式错误，请检查：" + ConStr);
            }
            String username = ConnetcArray[0];
            String pass = ConnetcArray[1];
            String port = ConnetcArray[2];
            String dbname = ConnetcArray[3];
            String DBUrl = "";
            if (assembletype.equals("mysql")) {
                DBUrl = "jdbc:mysql://";
            }
            if (assembletype.equals("oracle")) {
                DBUrl = "jdbc:oracle:thin:@//";
            }
            if (assembletype.equals("pgsql")) {
                DBUrl = "jdbc:pgsql://";
            }
            if (assembletype.equals("金仓")) {
                DBUrl = "jdbc:kingbase8://";
            }
            String Url = "";
            if (visittype.equalsIgnoreCase("IP")) {
                Machine machine = machineService.getBy("id", machineid);
                if (machine == null) {
                    return ResultGenerator.genFailedResult(machinename + " 该服务器不存在，请检查是否已经被删除！");
                }
                Url = machine.getIp();
                DBUrl = DBUrl + Url + ":" + port + "/" + dbname;
            } else {
                String domain = param.get("domain").toString();
                Url = domain;
                DBUrl = DBUrl + Url + "/" + dbname;
            }
            String LastDBUrl = "";
            if (assembletype.equals("mysql")) {
                LastDBUrl = DBUrl + "?useUnicode=true&useSSL=false&allowMultiQueries=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=UTC";
            } else {
                LastDBUrl = DBUrl;
            }
            Connection conn = null;
            try {
                EnviromentAssembleController.log.info("assembletype is:"+assembletype);
                EnviromentAssembleController.log.info("LastDBUrl is:"+LastDBUrl);
                conn = DriverManager.getConnection(LastDBUrl, username, pass);//获取连接
            } catch (Exception ex) {
                return ResultGenerator.genFailedResult("连接失败,请检查连接字：" + DBUrl + " ，异常原因：" + ex.getMessage());
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return ResultGenerator.genOkResult("连接成功！");
    }
}
