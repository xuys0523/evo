package com.github.longkerdandy.evo.service.sms.gateway;

/**
 * Json result from YunPian
 */
public class YunPianResult {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}