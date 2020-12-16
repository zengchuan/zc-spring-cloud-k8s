package com.zengc.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModelProperty;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultInfo<T> implements Serializable {
    private int code;//返回码
    private String msg; //返回信息
    private String method; //响应方法
    @ApiModelProperty
    private T data;//返回数据

    public ResultInfo() {

    }

    public ResultInfo(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }


    public ResultInfo setCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResultInfo setData(String key, Object obj) {
        if (data == null) {
            data = (T)new HashMap<>();
        }
        if (data instanceof Map) {
            ((Map) data).put(key, obj);
        }
        return this;
    }

    public ResultInfo setData(T obj) {
        data = obj;
        return this;
    }

    public Object getData() {
        return data;
    }


    public String getMethod() {
        return method;
    }

    public ResultInfo setMethod(String method) {
        this.method = method;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultInfo setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * @return
     * @Description 转换为json字符串
     */

    public String toJsonString(Object... obj) {
        return obj != null && obj.length > 0 ? JSON.toJSONString(this, SerializerFeature.WriteMapNullValue) : JSON.toJSONString(this);
    }

}
