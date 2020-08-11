package com.kevin.common.bo;


import com.kevin.common.enums.CustomRespCodeEnum;
import lombok.Data;

/** 
 * @Description: 应答结果基类对象
 * @author: libingtan
 * @date: 2020年1月10日 下午2:18:38  
 */
@Data
public class BaseResBO<T> {
	
	/**
	 * 响应码
	 */
	private String resCode;
	
	/**
	 * 描述信息
	 */
	private String resMsg;
	
	/**
	 * 应答业务参数（json串）
	 */
	private T resData;
	
	/**
	* @Description: 根据异常枚举设置响应信息  
	* @param resEnum
	* @return void
	* @throws  
	* @author: lxy
	* @date: 2019年7月9日 下午2:47:15
	*/
	public void setResEnum(CustomRespCodeEnum resEnum) {
		this.setResCode(resEnum.getValue());
		this.setResMsg(resEnum.getDisplayName());
	}
}