package com.zangs.crow.base.bean;

import com.zangs.crow.common.util.ResultUtil;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;

/**
 * @author wizzer
 * @date 2016/12/21
 */
public class BasicResult<T> {

    public static final String NO_MSG = "300";
    public static final String NO_DATA = "301";

    private Integer code;
    private String msg;
    private T data;

    public BasicResult() {

    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> BasicResult<T> NEW() {
        return new BasicResult<>();
    }


    public BasicResult addCode(int code) {
        this.code = code;
        return this;
    }

    public BasicResult addMsg(String msg) {
        if (Strings.isBlank(msg)) {
            this.msg = "";
        } else {
            this.msg = msg;
        }
        return this;
    }

    public BasicResult addData(T data) {
        this.data = data;
        return this;
    }

    public BasicResult(int code, String msg, T data) {
        this.code = code;
        if (Strings.isBlank(msg)) {
            this.msg = "";
        } else {
            this.msg = msg;
        }
        this.data = data;
    }


    public boolean isSuccess() {
        return getCode().equals(ResultUtil.SUCCESS);
    }

    public boolean isFailed() {
        return getCode().equals(ResultUtil.FAILED);
    }

    @Override
    public String toString() {
        return Json.toJson(this, JsonFormat.compact());
    }

}
