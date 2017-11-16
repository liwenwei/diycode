package com.example.wenwei.diycode_sdk.api.base.event;

import android.support.annotation.NonNull;

/**
 * 所有 Event 的基类
 * <p>
 * T 为对应的实体类
 * <p>
 * HTTP Status
 * -1 - 可能是网络未连接
 * 200, 201 - 请求成功，或执行成功
 * 400 - 参数不符合 API 的要求、或者数据格式验证没有通过，请配合 Response Body 里面的 error 信息确定问题。
 * 401 - 用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token。
 * 403 - 当前用户对资源没有操作权限
 * 404 - 资源不存在。
 * 500 - 服务器异常
 */
public class BaseEvent<T> {
    protected String uuid = "";         // 通用唯一识别码 (Universally Unique Identifier)
    protected boolean ok = false;       // 是否获取实体数据(T)成功
    protected Integer code = -1;        // 状态码
    protected T t;                      // 实体类

    /**
     * @param uuid 唯一识别码
     */
    public BaseEvent(@NonNull String uuid) {
        this.ok = false;
        this.uuid = uuid;
    }

    /**
     * @param uuid 唯一识别码
     * @param code 网络返回码
     * @param t    实体数据
     */
    public BaseEvent(@NonNull String uuid, @NonNull Integer code, @NonNull T t) {
        this.ok = null != t;
        this.uuid = uuid;
        this.code = code;
        this.t = t;
    }

    public BaseEvent setEvent(@NonNull Integer code, @NonNull T t) {
        this.ok = null != t;
        this.code = code;
        this.t = t;
        return this;
    }

    /**
     * 判断是否获取实体类成功
     *
     * @return ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * 获取实体类
     *
     * @return 实体类数据(可能为null)
     */
    public T getBean() {
        return t;
    }


    /**
     * 获取当前 Event 的 uuid
     *
     * @return uuid
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * 获取返回码详情
     *
     * @return 描述信息
     */
    public String getCodeDescribe() {
        switch (code) {
            case -1:
                return "可能是网络未连接，或者数据转化失败";
            case 200:
            case 201:
                return "请求成功，或执行成功。";
            case 400:
                return "参数不符合 API 的要求、或者数据格式验证没有通过";
            case 401:
                return "用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token";
            case 403:
                return "当前用户对资源没有操作权限";
            case 404:
                return "资源不存在";
            case 500:
                return "服务器异常";
            case 402:
                return "用户尚未登录";
            default:
                return "未知异常(" + code + ")";
        }
    }
}
