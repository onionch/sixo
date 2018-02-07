package com.onionch.webapp.website.bean;

public class RestResponse {

    private Integer code;
    private String message;
    private Object data;

    public RestResponse() {

    }

    public RestResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResponse response(Integer code, String message, Object data) {
        return new RestResponse(code, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
