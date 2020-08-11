package com.kevin.common.enums;

public class RespCodeEnum {

	/**
	 * 通用（成功）
	 */
	public static final RespCodeEnum SUCCESS=new RespCodeEnum("000000","成功");
	/**
	 * 通用（处理中）
	 */
	public static final RespCodeEnum UNKNOWN=new RespCodeEnum("999999","结果未知");

	/**
	 * 通用（失败）
	 */
	public static final RespCodeEnum FAIL=new RespCodeEnum("000001","失败");

	private String value;//应答编码
	private String displayName;//应答码含义

	private RespCodeEnum(){

	}
	private RespCodeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public static RespCodeEnum parseOf(String returnCode) {
        for (RespCodeEnum item : RespCodeEnum.values()){
            if (item.getValue().equals(returnCode)){
                return item;
            }
        }
        throw new IllegalArgumentException("未找到[" + returnCode + "]对应响应码!");
    }

	private static RespCodeEnum[] values() {
		return new RespCodeEnum[]{SUCCESS,UNKNOWN,FAIL};
	}
}
