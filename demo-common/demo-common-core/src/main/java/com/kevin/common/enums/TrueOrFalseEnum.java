package com.kevin.common.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @Description: 针对是或否业务判断的公共枚举
 * @author: YULONG.DU
 * @date: 2018年11月6日 上午9:33:39
 */
public enum TrueOrFalseEnum{
	
	TRUE( "1", "是" ),
	FALSE( "0", "否" );

	private String value;
	private final String displayName;

	private static Map<String, TrueOrFalseEnum> valueMap = new HashMap<String, TrueOrFalseEnum>();

	static {
		for ( TrueOrFalseEnum _enum : TrueOrFalseEnum.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	TrueOrFalseEnum( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return this.getDisplayName();
	}
	
	/**
	 * 枚举转换
	 */
	public static TrueOrFalseEnum parseOf( String value ) {
		for ( TrueOrFalseEnum item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
