package com.zoctan.api.entity;

import java.util.Date;
import javax.persistence.*;

public class Dubboapi {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * DeployUnitId
     */
    private Long deployunitid;

    /**
     * 发布单元名
     */
    private String deployunitname;


    public String getDgroup() {
        return dgroup;
    }

    public void setDgroup(String dgroup) {
        this.dgroup = dgroup;
    }

    private String dgroup;



    /**
     * 接口名
     */
    private String apiname;

    /**
     * 版本
     */
    private String version;

    /**
     * 响应数据格式，基础类型，对象
     */
    private String responecontenttype;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上一次修改时间
     */
    @Column(name = "lastmodify_time")
    private Date lastmodifyTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 项目id
     */
    private Long projectid;

    /**
     * 用例数量
     */
    private Long casecounts;

    /**
     * 模块名
     */
    private String modelname;

    /**
     * 模块id
     */
    private Long modelid;

    /**
     * 维护者
     */
    private String mnickname;

    /**
     * 维护者id
     */
    private Long mid;

    /**
     * 维护者id
     */
    private Long creatorid;

    /**
     * 获取Id
     *
     * @return id - Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置Id
     *
     * @param id Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取DeployUnitId
     *
     * @return deployunitid - DeployUnitId
     */
    public Long getDeployunitid() {
        return deployunitid;
    }

    /**
     * 设置DeployUnitId
     *
     * @param deployunitid DeployUnitId
     */
    public void setDeployunitid(Long deployunitid) {
        this.deployunitid = deployunitid;
    }

    /**
     * 获取发布单元名
     *
     * @return deployunitname - 发布单元名
     */
    public String getDeployunitname() {
        return deployunitname;
    }

    /**
     * 设置发布单元名
     *
     * @param deployunitname 发布单元名
     */
    public void setDeployunitname(String deployunitname) {
        this.deployunitname = deployunitname;
    }

    /**
     * 获取接口名
     *
     * @return apiname - 接口名
     */
    public String getApiname() {
        return apiname;
    }

    /**
     * 设置接口名
     *
     * @param apiname 接口名
     */
    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取响应数据格式，基础类型，对象
     *
     * @return responecontenttype - 响应数据格式，基础类型，对象
     */
    public String getResponecontenttype() {
        return responecontenttype;
    }

    /**
     * 设置响应数据格式，基础类型，对象
     *
     * @param responecontenttype 响应数据格式，基础类型，对象
     */
    public void setResponecontenttype(String responecontenttype) {
        this.responecontenttype = responecontenttype;
    }

    /**
     * 获取备注
     *
     * @return memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取上一次修改时间
     *
     * @return lastmodify_time - 上一次修改时间
     */
    public Date getLastmodifyTime() {
        return lastmodifyTime;
    }

    /**
     * 设置上一次修改时间
     *
     * @param lastmodifyTime 上一次修改时间
     */
    public void setLastmodifyTime(Date lastmodifyTime) {
        this.lastmodifyTime = lastmodifyTime;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取项目id
     *
     * @return projectid - 项目id
     */
    public Long getProjectid() {
        return projectid;
    }

    /**
     * 设置项目id
     *
     * @param projectid 项目id
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    /**
     * 获取用例数量
     *
     * @return casecounts - 用例数量
     */
    public Long getCasecounts() {
        return casecounts;
    }

    /**
     * 设置用例数量
     *
     * @param casecounts 用例数量
     */
    public void setCasecounts(Long casecounts) {
        this.casecounts = casecounts;
    }

    /**
     * 获取模块名
     *
     * @return modelname - 模块名
     */
    public String getModelname() {
        return modelname;
    }

    /**
     * 设置模块名
     *
     * @param modelname 模块名
     */
    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    /**
     * 获取模块id
     *
     * @return modelid - 模块id
     */
    public Long getModelid() {
        return modelid;
    }

    /**
     * 设置模块id
     *
     * @param modelid 模块id
     */
    public void setModelid(Long modelid) {
        this.modelid = modelid;
    }

    /**
     * 获取维护者
     *
     * @return mnickname - 维护者
     */
    public String getMnickname() {
        return mnickname;
    }

    /**
     * 设置维护者
     *
     * @param mnickname 维护者
     */
    public void setMnickname(String mnickname) {
        this.mnickname = mnickname;
    }

    /**
     * 获取维护者id
     *
     * @return mid - 维护者id
     */
    public Long getMid() {
        return mid;
    }

    /**
     * 设置维护者id
     *
     * @param mid 维护者id
     */
    public void setMid(Long mid) {
        this.mid = mid;
    }

    /**
     * 获取维护者id
     *
     * @return creatorid - 维护者id
     */
    public Long getCreatorid() {
        return creatorid;
    }

    /**
     * 设置维护者id
     *
     * @param creatorid 维护者id
     */
    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }
}