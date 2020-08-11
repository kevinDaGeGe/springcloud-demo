package com.kevin.common.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @Description: 请求参数基类业务对象
 * @author: YULONG.DU
 * @date: 2018年11月8日 下午4:38:31
 */
@Data
public class BaseReqBO {
	private static final String ERROR_PARAM_NOTNULL = "参数不能为空";
	private static final String ERROR_PARAM_LENGTH = "参数长度超过规定范围";
	
	@NotBlank(message="merchNo " + ERROR_PARAM_NOTNULL)
	@Size(max=9, message="merchNo " + ERROR_PARAM_LENGTH)
	private String merchNo;//商户号
	
	@NotBlank(message="msgId " + ERROR_PARAM_NOTNULL)
	@Size(max=32, message="msgId " + ERROR_PARAM_LENGTH)
	private String msgId;//商户请求流水
	
	@NotBlank(message="txCode " + ERROR_PARAM_NOTNULL)
	@Size(max=21, message="txCode " + ERROR_PARAM_LENGTH)
	private String txCode;//业务编码
	
	private String timestamp;

}
