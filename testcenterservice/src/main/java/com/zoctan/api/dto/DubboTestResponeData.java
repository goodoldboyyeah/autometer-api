package com.zoctan.api.dto;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.util.List;

public class DubboTestResponeData {

    public String getRequestData() {
        return RequestData;
    }

    public void setRequestData(String requestData) {
        RequestData = requestData;
    }

    private String RequestData;


    public String getResponeContent() {
        return ResponeContent;
    }

    public void setResponeContent(String responeContent) {
        ResponeContent = responeContent;
    }


    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    private String general;

    private String ResponeContent;

    public String getDeployunitName() {
        return DeployunitName;
    }

    public void setDeployunitName(String deployunitName) {
        DeployunitName = deployunitName;
    }

    private String DeployunitName;

    public String getInterfaceService() {
        return InterfaceService;
    }

    public void setInterfaceService(String interfaceService) {
        InterfaceService = interfaceService;
    }

    private String InterfaceService;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    private String Version;

    public long getResponeTime() {
        return ResponeTime;
    }

    public void setResponeTime(long responeTime) {
        ResponeTime = responeTime;
    }

    private long ResponeTime;

}


