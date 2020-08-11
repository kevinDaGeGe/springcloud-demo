/**   
 * Copyright © 2018 zlpay.
 */
package com.kevin.common.enums;

/** 
 * @Description:  客户系统应答码枚举--枚举命名以项目名简写作为第一个单词  + RespCodeEnum
 * 				  应答码分为三类:成功失败\失败\处理中,首字母分别用S\F\P代表.
 * 				  应答码分为四段:首字母是第一段-表示成功\失败\处理中;第2位字母是第二段-B代表业务S代表系统;
 * 	                           3~4两位数字是第三段，代表当前应答码的业务范围;5~7三位数字是第四段代表具体的错误
 * @author: syuf
 * @date: 2018年10月31日 上午10:20:58  
 */
public enum CustomRespCodeEnum {
	/**
	 * 通用（成功）
	 */
	SUCESS("S000000","成功"),
	/**
	 * 通用（失败）
	 */
	FS00001("FS00001","失败"),
	FB00001("FB00001","失败"),
	/**
	 * 通用（处理中）
	 */
	PS00001("PS00001","系统异常"),
	PB00001("PB00001","结果未知"),
	
	/**
	 * 证书相关应答码
	 */
	FB01001("FB01001","商户证书不存在"),
	FB01002("FB01002","同一个证书序列号下有多个证书"),
	
	/**
	 * 参数校验应答码
	 */
	FB02001("FB02001","参数校验失败"),
	FB02002("FB02002","请求参数丢失"),
	
	/**
	 * 商户相关应答码
	 */
	FB03001("FB03001","商户信息不存在"),
	FB03002("FB03002","机构信息不存在"),
	FB03003("FB03003","柜台信息不存在"),
	FB03004("FB03004","账户类型不存在"),
	/**
	 * 短信相关应答码
	 */
	FB04001("FB04001","短信发送未知异常"),
	FB04002("FB04002","短信发送失败"),
	FB04003("FB04003","短信校验失败"),
	
	/**
	 * 客户校验相关应答码
	 */
	FB05001("FB05001","您输入的信息不匹配，请仔细核对企业信息！\r\n" + 
			"如有疑问，请联系客服处理！"),
	FB05002("FB05002","该客户无法进行注册，请联系客服处理。"),
	FB05003("FB05003","存在多个有效客户信息，请联系客服处理。"),
	FB05004("FB06004","更新客户等级失败"),
	FB05005("FB06005","更新客户证件有效期失败"),
	FB05006("FB05006","开户认证中"),
	FB05007("FB05007","开卡失败，已绑其他卡"),
	
	FB06001("FB06001","该客户不属于任何商户"),
	FB06002("FB06002","该客户号非法"),
	FB06003("FB06003","该客户关联商户信息非法"),
	/**
	 * 客户等级提升相关应答码
	 */
	FB07001("FB07001","保存申请信息失败"),
	FB07002("FB07002","更新申请信息失败"),
	FB07003("FB07003","主键ID不能为空"),
	FB07004("FB07004","查询申请信息失败"),

	/**
	 * 风控校验
	 */
	FB08001("FB08001","黑名单检查未通过"),
	
	/**
	 * 银行卡类应答码
	 */
	FB09001("FB09001","银行卡主键不能为空"),
	;

	private String value;//应答编码
	private String displayName;//应答码含义
	
	CustomRespCodeEnum(String value,String displayName) {
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
}
