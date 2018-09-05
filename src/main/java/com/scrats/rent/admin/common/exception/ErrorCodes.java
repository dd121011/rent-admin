package com.scrats.rent.admin.common.exception;

import java.util.Objects;

public enum ErrorCodes {
    PARAM_PARSE_ERROR(30001,"参数解析错误"),
    JSON_PARSE_ERROR(30002,"JSON转换失败"),
    EXISTED_ERROR(50001,"重复添加"),
    USER_NOT_FOUND_ERROR(50002,"用户不存在"),
    PWD_ERROR(50003,"原密码错误"),
    GET_CODE_ERROR(50004,"获取短信验证码失败"),
    VERIFY_MOBILE_CODE_ERROR(50005,"短信验证码错误"),
    VERIFY_CODE_ERROR(50006,"验证失败"),
    USER_EXIST_ERROR(50007,"用户名已存在"),
    AUTHENTICATION_ERROR(50012, "非法令牌或已失效"),
    TOKEN_CREATE_ERROR(50013, "令牌生成失败"),
    UN_LOGIN_ERROR(50008,"用户未登录"),
    USER_PWD_ERROR(50009, "用户名或密码错误"),
    INIT_INTENT_ERROR(50010, "初始化意图失败"),
    UPLOAD_ERROR(50011, "上传失败"),
    UNKNOWN_ERROR(70001,"未知错误"),
    HTTP_ERROR(80001,"HTTP相关错误"),
    SERVICE_ERROR(90000,"服务端内部错误"),
    THIRD_PARTY_ERROR(90001,"远程服务错误:"),
    REMOTE_SERVICE_ERROR(90002,"远程服务错误");

	private Integer code;
	private String errorInfo;

	ErrorCodes(Integer code, String errorInfo){
		this.code = code ;
		this.errorInfo = errorInfo;
	}

	public int getErrorCode() {
		return code;
	}

	public String getInfo() {
		return toString();
	}

	@Override
	public String toString() {
		return errorInfo;
	}

	public static ErrorCodes fromErrorCode(Integer code){
		for (ErrorCodes error : ErrorCodes.values()) {
			if (Objects.equals(code,error.getErrorCode())) {
				return error;
			}
		}
		return null;
	}

}
