package com.zoctan.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wsapi_params")
public class WsapiParams {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * apiId
     */
    private Long apiid;

    /**
     * api名
     */
    private String apiname;

    /**
     * 发布单元Id
     */
    private Long deployunitid;

    /**
     * 发布单元名
     */
    private String deployunitname;

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
     * 维护者id
     */
    private Long creatorid;

    /**
     * Key默认值
     */
    private String keydefaultvalue;

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
     * 获取apiId
     *
     * @return apiid - apiId
     */
    public Long getApiid() {
        return apiid;
    }

    /**
     * 设置apiId
     *
     * @param apiid apiId
     */
    public void setApiid(Long apiid) {
        this.apiid = apiid;
    }

    /**
     * 获取api名
     *
     * @return apiname - api名
     */
    public String getApiname() {
        return apiname;
    }

    /**
     * 设置api名
     *
     * @param apiname api名
     */
    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    /**
     * 获取发布单元Id
     *
     * @return deployunitid - 发布单元Id
     */
    public Long getDeployunitid() {
        return deployunitid;
    }

    /**
     * 设置发布单元Id
     *
     * @param deployunitid 发布单元Id
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

    /**
     * 获取Key默认值
     *
     * @return keydefaultvalue - Key默认值
     */
    public String getKeydefaultvalue() {
        return keydefaultvalue;
    }

    /**
     * 设置Key默认值
     *
     * @param keydefaultvalue Key默认值
     */
    public void setKeydefaultvalue(String keydefaultvalue) {
        this.keydefaultvalue = keydefaultvalue;
    }
}