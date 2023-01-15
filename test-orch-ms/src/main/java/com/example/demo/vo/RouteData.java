package com.example.demo.vo;


import java.util.List;

public class RouteData {
    private String key;
    private boolean keyMatch;
    private String url;
    private String method;
    private List<String> pathParams;
    private List<String> queryParams;
    private String payload;

    public String getKey() {
        return key;
    }

    public void setKey(String id) {
        this.key = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public List<String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(List<String> pathParams) {
        this.pathParams = pathParams;
    }

    public List<String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isKeyMatch() {
        return keyMatch;
    }

    public void setKeyMatch(boolean keyMatch) {
        this.keyMatch = keyMatch;
    }

    @Override
    public String toString() {
        return "RouteInfo{" +
                "key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", keyMatch='" + keyMatch + '\'' +
                ", queryParams=" + queryParams +
                ", pathParams=" + pathParams +
                ", payload='" + payload + '\'' +
                '}';
    }
}