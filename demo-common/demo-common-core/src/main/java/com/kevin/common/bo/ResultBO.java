package com.kevin.common.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @Description: 接口返回对象公用业务对象
 * @author: YULONG.DU
 * @date: 2018年11月5日 上午10:25:28 
 * @param <T>
 * @param <K>
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
public class ResultBO<T,K> extends BaseResultBO<K> {

	T data;
	
	public ResultBO(){
	}
	public ResultBO(K k){
		super.setRespCode(k);
	}
	public ResultBO(T t,K k){
		this.data = t;
		super.setRespCode(k);
	}

}
