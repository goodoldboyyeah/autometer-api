package com.zoctan.api.dto;

import com.zoctan.api.entity.Registercenterinterface;

public class RegistercenterinterfaceDto extends Registercenterinterface {
    public long getDeployunitid() {
        return deployunitid;
    }

    public void setDeployunitid(long deployunitid) {
        this.deployunitid = deployunitid;
    }

    private long deployunitid;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private  String creator;

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    private  String servicetype;


}
