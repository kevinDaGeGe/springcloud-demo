package com.kevin.common.bo;

import lombok.Data;

/**
 * 
 * @Description: 应答结果基类对象
 * @author: YULONG.DU
 * @date: 2018年11月5日 上午10:22:49 
 * @param <K>
 */
@Data
public class BaseResultBO<K> {
	
	private K respCode;
	
	private String respMsg;

	public void setReturn(K respCode, String respMsg) {
		this.respCode = respCode ;
		this.respMsg = respMsg ;
	}

}
