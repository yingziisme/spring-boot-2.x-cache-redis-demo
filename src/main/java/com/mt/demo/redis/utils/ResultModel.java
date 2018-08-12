package com.mt.demo.redis.utils;

import lombok.Data;

/**
 * ResultModel
 *
 * @author MT.LUO
 * 2018/8/6 22:45
 * @Description:
 */
@Data
public class ResultModel {

    private int code;
    private String msg;
    private Object object;

    public ResultModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }


    public static ResultModel ok() {
        return new ResultModel(200, "success");
    }

    public static ResultModel ok(Object object) {
        return new ResultModel(200, "success", object);
    }
}
