package com.kevin.common.bo;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @Description: 列表应答结果公用对象
 * @author: YULONG.DU
 * @date: 2018年11月5日 上午10:23:40 
 * @param <T>
 * @param <K>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ListResultBO<T,K> extends BaseResultBO<K> {
	
	List<T> listData;
}
