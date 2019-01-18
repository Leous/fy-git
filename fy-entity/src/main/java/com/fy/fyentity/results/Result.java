package com.fy.fyentity.results;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-07 0:53
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2399472960127281973L;

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 返回码
     */
    private String returnCode;

    /**
     * 返回信息
     */
    private String returnMessage;

    /**
     * 结果集
     */
    private T entry;

    public Result() {
        this.isSuccess = false;
    }

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess, String returnMessage, String returnCode){
        this.isSuccess = isSuccess;
        this.returnMessage = returnMessage;
        this.returnCode = returnCode;
    }
}
