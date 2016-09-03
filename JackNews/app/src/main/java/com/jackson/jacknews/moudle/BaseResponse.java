package com.jackson.jacknews.moudle;

/**
 * Created by 90720 on 2016/8/26.
 */
public class BaseResponse {

    /**
     * code : 200
     * msg : success
     *
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
