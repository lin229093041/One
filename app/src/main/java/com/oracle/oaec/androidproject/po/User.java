package com.oracle.oaec.androidproject.po;

/**
 * Created by linruoyu on 2016/7/11.
 */

public class User {

    /**
     * name : 1234
     * password : 123
     * img : http://10.0.12.102:8093/OneAndroidDemo/touxiangimg/t1.jpg
     */

    private String name;
    private String password;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
