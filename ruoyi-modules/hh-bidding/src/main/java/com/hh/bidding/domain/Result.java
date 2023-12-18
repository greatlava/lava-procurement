package com.hh.bidding.domain;

import java.io.Serializable;

public class Result implements Serializable {
    private String name;
    private String url;

    private Long size;

    public String getName() {
        return name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
