package com.oracle.oaec.androidproject.po;

import java.util.ArrayList;

/**
 * Created by linruoyu on 2016/6/30.
 */

public class CaiPin {
    private String cailei;
    private ArrayList<CaipinMsg> msg;

    public CaiPin(String cailei, ArrayList<CaipinMsg> msg) {
        this.cailei = cailei;
        this.msg = msg;
    }
    public String getCailei() {
        return cailei;
    }

    public void setCailei(String cailei) {
        this.cailei = cailei;
    }

    public ArrayList<CaipinMsg> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<CaipinMsg> msg) {
        this.msg = msg;
    }
}
