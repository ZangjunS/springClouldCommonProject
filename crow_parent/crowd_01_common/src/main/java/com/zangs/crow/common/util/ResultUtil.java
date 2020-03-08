package com.zangs.crow.common.util;

import com.zangs.crow.base.bean.BasicResult;


/**
 *
 * @author zangs
 */
public class ResultUtil {
    public static final Integer SUCCESS = 200;
    public static final Integer FAILED = 500;

    public static <T> BasicResult<T> success(String content) {
        return new BasicResult<T>(SUCCESS, content, null);
    }

    public static <T> BasicResult<T> success(String content, T data) {
        return new BasicResult<T>(SUCCESS, content, data);
    }

    public static <T> BasicResult<T> error(int code, String content) {
        return new BasicResult<>(code, content, null);
    }

    public static <T> BasicResult<T> error(String content) {
        return new BasicResult<>(FAILED, content, null);
    }

    public static <T> BasicResult<T> success() {
        return new BasicResult<>(SUCCESS, "system.success", null);
    }

    public static <T> BasicResult<T> error() {
        return new BasicResult<T>(FAILED, "system.error", null);
    }


    public static <T> BasicResult<T> successNoData() {
        return success("ok");
    }

    public static <T> BasicResult<T> successWithData(T data) {
        return  success("ok", data);
    }

    public static  <T> BasicResult<T> failed(String message) {
        return error(message);

    }

    public static <T> BasicResult<T> failed() {
        return error();

    }
}
