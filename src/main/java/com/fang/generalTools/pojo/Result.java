package com.fang.generalTools.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: RFIDsystem
 * @description: 结果实体类
 * @author: Mr.Fu
 * @create: 2021-02-08 13:40
 **/
@Data
public class Result implements Serializable {
    /**
     *
     * XXX class function description.
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS_CODE = 200;

    private static final String SUCCESS_MSG = "成功";

    private static final Integer FAIL_CODE = 400;

    private static final String FAIL_MSG = "失败";

    private static final Integer ERROR_CODE = 500;

    private static final String ERROR_MSG = "报错,错误信息如下:";
    /**
     *结果信息
     */
    private String msg;
    /**
     *结果码
     */
    private Integer code;
    /**
     *返回值
     */
    private Object data;

    public Result(){}

    public Result(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Result success(Object data){
        this.msg = SUCCESS_MSG;
        this.code = SUCCESS_CODE;
        this.data = data;
        return new Result(msg , code , data);
    }

    public Result success(){
        this.msg = SUCCESS_MSG;
        this.code = SUCCESS_CODE;
        return new Result(msg , code);
    }

    public Result fail(){
        this.msg = FAIL_MSG;
        this.code = FAIL_CODE;
        return new Result(msg , code);
    }

    public Result error(String errorMsg){
        this.msg = ERROR_MSG + errorMsg;
        this.code = ERROR_CODE;
        return new Result(msg , code);
    }
}
