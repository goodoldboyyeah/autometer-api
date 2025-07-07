package com.zoctan.api.entity;

import java.util.Date;
import javax.persistence.*;

public class Registercentermethods {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * InterfaceId
     */
    private Long interfaceid;

    /**
     * 方法名
     */
    private String methodname;

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
     * 获取InterfaceId
     *
     * @return interfaceid - InterfaceId
     */
    public Long getInterfaceid() {
        return interfaceid;
    }

    /**
     * 设置InterfaceId
     *
     * @param interfaceid InterfaceId
     */
    public void setInterfaceid(Long interfaceid) {
        this.interfaceid = interfaceid;
    }

    /**
     * 获取方法名
     *
     * @return methodname - 方法名
     */
    public String getMethodname() {
        return methodname;
    }

    /**
     * 设置方法名
     *
     * @param methodname 方法名
     */
    public void setMethodname(String methodname) {
        this.methodname = methodname;
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