package com.zoctan.api.dto;

import com.zoctan.api.entity.DeployunitModel;

public class DeployunitModelDto extends DeployunitModel {


    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(long creatorid) {
        this.creatorid = creatorid;
    }

    public String getMnickname() {
        return mnickname;
    }

    public void setMnickname(String mnickname) {
        this.mnickname = mnickname;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    private long  mid;
    private long  creatorid;
    private String  mnickname;
    private String  creator;

}
