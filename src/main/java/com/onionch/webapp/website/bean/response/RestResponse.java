package com.onionch.webapp.website.bean.response;

import com.onionch.webapp.website.bean.enums.ResultCode;

public class RestResponse {

    private Integer code;
    private String message;
    private Object data;

    public RestResponse() {

    }

    public static RestResponse success(Object data){
        RestResponse restResponse=new RestResponse();
        restResponse.setResultCode(ResultCode.SUCCESS);
        restResponse.setData(data);
        return restResponse;
    }

    public static RestResponse failure(ResultCode resultCode){
        RestResponse restResponse=new RestResponse();
        restResponse.setResultCode(resultCode);
        return restResponse;
    }

    public static RestResponse failure(ResultCode resultCode,Object data){
        RestResponse restResponse=new RestResponse();
        restResponse.setResultCode(resultCode);
        restResponse.setData(data);
        return restResponse;
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

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
