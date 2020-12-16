/**
 *
 */
package com.zengc.core.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CheckInput {
    private JSONObject input;

    private CheckReturn checkReturn;

    // 字段选择枚举类
    public enum KeySelect {
        /**
         *
         */
        REQUIRE,
        /**
         *
         */
        OPZIO;
    }

    // 字段类型枚举类
    public enum KeyType {
        /**
         *
         */
        STRING, INTEGER, NUMBER, POSITIVEINTEGER, BOOL;
    }

    public CheckInput() {
        super();
    }

    public CheckInput(JSONObject input) {
        super();
        this.input = input;
    }

    /**
     * params: ks:KeySelect，字段选择 kt:KeyType， 字段名类型 key:String， 字段名
     */
    public CheckReturn checkKey(KeySelect ks, KeyType kt, String key) {
        this.checkReturn = new CheckReturn(false, false);
        if (ks == null || kt == null || key == null) {
            return null;
        }
        switch (ks) {
            case REQUIRE:
                this.checkRequire(kt, key);
                break;
            case OPZIO:
                this.checkOpzio(kt, key);
                break;
            default:
                this.checkRequire(kt, key);
        }
        return this.checkReturn;
    }

    private void checkRequire(KeyType kt, String key) {
        if (!input.containsKey(key)) {
            this.checkReturn.setCheckReturn(1,"必选参数" + key + "未提供",false,false);
            return;
        }
        if (StringUtils.isEmpty(input.getString(key))) {
            this.checkReturn.setCheckReturn(1,"必选参数" + key + "不能为空",false,false);
            return;
        }
        this.checkReturn.setSuccess(true).setUserable(true);
        switch (kt) {
            // 字符串类型
            case STRING:
                break;
            // 布尔类型
            case BOOL:
                if (!BooleanUtils.toBoolean(input.getString(key))) {
                    this.checkReturn.setCheckReturn(1,"必选参数" + key + "不能转换成bool型",false,false);
                }
                break;
            // 整数类型
            case INTEGER:
                if (!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCheckReturn(1,"必选参数" + key + "必须为整数",false,false);
                }
                break;
            // 数值类型
            case NUMBER:
                if (!NumberUtils.isNumber(input.getString(key))) {
                    this.checkReturn.setCheckReturn(1,"必选参数" + key + "必须为数值",false,false);
                }
                break;
            // 正整数类型
            case POSITIVEINTEGER:
                if (!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCheckReturn(1,"必选参数" + key + "必须为整数",false,false);
                } else if (Long.parseLong(input.getString(key)) < 1) {
                    this.checkReturn.setCheckReturn(1,"必选参数" + key + "必须为大于0的整数",false,false);
                }
                break;
            default:
                this.checkReturn.setSuccess(true);
        }
    }

    private void checkOpzio(KeyType kt, String key) {

        if (!input.containsKey(key)) {
            this.checkReturn.setSuccess(true);
        }
        switch (kt) {
            // 字符串类型
            case STRING:
                 if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 布尔类型
            case BOOL:
                if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else {
                    this.checkReturn.setSuccess(true);
                    // 判断是否能转成bool型
                    if (BooleanUtils.toBoolean(input.getString(key))) {
                        this.checkReturn.setUserable(true);
                    }
                }
                break;
            // 整数类型
            case INTEGER:
                if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else if (!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为整数");
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 数值类型
            case NUMBER:
                if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else if (!NumberUtils.isNumber(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为数值");
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            // 正整数类型
            case POSITIVEINTEGER:
                if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else if (!NumberUtils.isDigits(input.getString(key))) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为整数");
                } else if (Long.parseLong(input.getString(key)) < 1) {
                    this.checkReturn.setCode(1);
                    this.checkReturn.setMsg("可选参数" + key + "必须为大于0的整数");
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
                break;
            default:
                if (StringUtils.isEmpty(input.getString(key))) {
                    this.checkReturn.setSuccess(true);
                } else {
                    this.checkReturn.setSuccess(true);
                    this.checkReturn.setUserable(true);
                }
        }
    }

    public JSONObject getInput() {
        return input;
    }

    public CheckInput setInput(JSONObject input) {
        this.input = input;
        return this;
    }

    public CheckReturn getCheckReturn() {
        return checkReturn;
    }

    public CheckInput setCheckReturn(CheckReturn checkReturn) {
        this.checkReturn = checkReturn;
        return this;
    }
}
