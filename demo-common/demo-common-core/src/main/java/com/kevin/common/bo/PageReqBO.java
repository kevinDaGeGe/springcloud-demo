package com.kevin.common.bo;

import lombok.Data;

/**
 * 
 * @Description: 分页查询条件公用业务对象
 * @author: YULONG.DU
 * @date: 2018年11月5日 上午10:24:35 
 * @param <T>
 */
@Data
public class PageReqBO<T> {
	
	private PageBO pageBO;
	
	private T condition;
}
