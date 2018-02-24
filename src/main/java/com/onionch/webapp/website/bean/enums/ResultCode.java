package com.onionch.webapp.website.bean.enums;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "处理成功"),

    /* 系统错误状态码 */
    SYSTEM_ERROR(500, "系统错误"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 菜单错误：20001-29999　*/
    MENU_NOT_EXIST(20001,"菜单不存在"),

    /* 角色错误：30001-39999　*/
    ROLE_NOT_EXIST(30001,"角色不存在");


    private Integer code;

    private String message;

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

    ResultCode(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
