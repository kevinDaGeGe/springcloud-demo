package com.kevin.common.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @Description: 证件类型
 * @author: tsl
 * @date: 2020年2月19日 下午5:22:18  
 */
public enum CertificationTypeEnum {

	P01( "P01", "居民身份证","00" ),
	C15( "C15", "社会信用代码","65" ),
	P31( "P31", "护照","01" ),
	P35( "P35", "外国护照","12" ),
	P41( "P41", "港澳台居民来往内地通行证","13"),
	C101( "C101", "外国人永久居留证","14" ),
	C102( "C102", "台湾通行证","15" )
	;
	
	private String value;
	private final String displayName;
	private final String orgValue;

	private static Map<String, CertificationTypeEnum> valueMap = new HashMap<String, CertificationTypeEnum>();

	static {
		for ( CertificationTypeEnum _enum : CertificationTypeEnum.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	CertificationTypeEnum( String value, String displayName, String orgValue) {
		this.value = value;
		this.displayName = displayName;
		this.orgValue=orgValue;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getOrgValue() {
		return orgValue;
	}

	public static CertificationTypeEnum returnEnum( String value ) {
		return valueMap.get( value );
	}
	/**
	 * 枚举转换
	 */
	public static CertificationTypeEnum parseOf( String value ) {
		for ( CertificationTypeEnum item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
	@Override
	public String toString() {
		return this.getDisplayName();
	}
}