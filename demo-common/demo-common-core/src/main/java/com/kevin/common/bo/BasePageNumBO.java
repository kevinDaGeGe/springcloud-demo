package com.kevin.common.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @Description: 查询分页数公共应答对象
 * @author: YULONG.DU
 * @date: 2019年10月23日 下午3:06:10
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BasePageNumBO extends AbstractBO{
	
	/**
	 * 总个数
	 */
	private String totalNum;
	
	/**
	 * 总页数
	 */
	private String totalPageNum;
	
}
