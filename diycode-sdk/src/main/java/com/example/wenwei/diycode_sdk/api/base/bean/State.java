package com.example.wenwei.diycode_sdk.api.base.bean;

import java.io.Serializable;

/**
 * 判断操作状态是否成功
 */
public class State implements Serializable {
    /**
     * ok : 1
     */

    private int ok;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
}
