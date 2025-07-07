package com.zoctan.api.entity;

import java.util.Date;
import javax.persistence.*;

public class Registercenterinterface {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    private Long projectid;



    /**
     * Interface
     */
    private String interfacename;

    /**
     * 版本
     */
    private String version;

    /**
     * 分组
     */
    private String dgroup;

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
     * 获取Interface
     *
     * @return interfacename - Interface
     */
    public String getInterfacename() {
        return interfacename;
    }

    /**
     * 设置Interface
     *
     * @param interfacename Interface
     */
    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename;
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
     * 获取分组
     *
     * @return dgroup - 分组
     */
    public String getDgroup() {
        return dgroup;
    }

    /**
     * 设置分组
     *
     * @param dgroup 分组
     */
    public void setDgroup(String dgroup) {
        this.dgroup = dgroup;
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
}