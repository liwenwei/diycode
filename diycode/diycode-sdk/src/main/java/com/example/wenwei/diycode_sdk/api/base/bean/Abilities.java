package com.example.wenwei.diycode_sdk.api.base.bean;

import java.io.Serializable;


/**
 * 权限控制
 */
public class Abilities implements Serializable {

    private boolean update;
    private boolean destroy;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
