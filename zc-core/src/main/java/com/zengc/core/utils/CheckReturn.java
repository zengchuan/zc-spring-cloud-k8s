/**
 *
 */
package com.zengc.core.utils;


public class CheckReturn {
    private Integer code; // 校验后返回给客户端的code,0=正常，1=异常，2=...

    private String msg; // 校验后的返回信息

    private boolean success; // 校验参数通过与否true=通过，false=不通过

    private boolean userable; // 是否能取值true=能，false=不能

    public CheckReturn() {
        super();
    }

    public CheckReturn(boolean success) {
        super();
        this.success = success;
    }

    public CheckReturn(boolean success, boolean userable) {
        super();
        this.success = success;
        this.userable = userable;
    }

    public CheckReturn(Integer code, String msg, boolean success, boolean userable) {
        super();
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.userable = userable;
    }
    public CheckReturn setCheckReturn(Integer code, String msg, boolean success, boolean userable) {

        this.code = code;
        this.msg = msg;
        this.success = success;
        this.userable = userable;
        return this;
    }


    public Integer getCode() {
        return code;
    }

    public CheckReturn setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CheckReturn setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public CheckReturn setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public boolean isUserable() {
        return userable;
    }

    public CheckReturn setUserable(boolean userable) {
        this.userable = userable;
        return this;
    }
}
