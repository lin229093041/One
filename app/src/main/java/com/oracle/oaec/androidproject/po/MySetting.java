package com.oracle.oaec.androidproject.po;

/**
 * Created by Administrator on 2016/7/11.
 */

public class MySetting {

    private String name;
    private String content;

    public MySetting(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
