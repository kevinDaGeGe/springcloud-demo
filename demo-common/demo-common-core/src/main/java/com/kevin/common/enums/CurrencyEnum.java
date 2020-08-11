package com.kevin.common.enums;

public enum CurrencyEnum {
	CURRENCY_CNY("01","CNY" ,"人民币"), 
	CURRENCY_GBP("12","GBP", "英镑"),
	CURRENCY_HKD("13","HKD" ,"港币"), 
	CURRENCY_USD("14","USD" ,"美元"), 
	CURRENCY_CHF("15","CHF" ,"瑞士法郎"), 
	CURRENCY_SEK("21","SEK" ,"瑞典克郎"), 
	CURRENCY_DKK("22","DKK" ,"丹麦克郎"), 
	CURRENCY_NOK("23","NOK" ,"挪威克朗"), 
	CURRENCY_JPY("27","JPY" ,"日元"), 
	CURRENCY_CAD("28","CAD" ,"加拿大元"), 
	CURRENCY_AUD("29","AUD" ,"澳大利亚元"), 
	CURRENCY_SGD("32","SGD" ,"新加坡元"), 
	CURRENCY_EUR("38","EUR" ,"欧元"), 
	CURRENCY_MOP("81","MOP" ,"澳门元"),
	CURRENCY_THB("84","THB" ,"泰铢")
	;

	private String value;
	private String currency;
	private String displayName;

	public String getValue() {
		return this.value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getCurrency() {
		return currency;
	}

	CurrencyEnum(String value,String currency, String displayName) {
		this.value = value;
		this.currency = currency;
		this.displayName = displayName;
	}

	/**
	 * 枚举转换
	 */
	public static CurrencyEnum parseOfValue( String value ){
		for( CurrencyEnum item:values() )
			if( item.getValue().equals(value) )
				return item;
		
		throw new IllegalArgumentException("枚举值["+value+"]不匹配!");
	}
	/**
	 * 枚举转换
	 */
	public static CurrencyEnum parseOfCurrency( String currency ){
		for( CurrencyEnum item:values() )
			if( item.getCurrency().equals(currency) )
				return item;
		
		throw new IllegalArgumentException("枚举值["+currency+"]不匹配!");
	}
	/**
	 * 获取备付金币别
	 */
	public static CurrencyEnum[] currencysValues() {
		CurrencyEnum[] currencys = new CurrencyEnum[] { CURRENCY_CNY, CURRENCY_GBP, CURRENCY_HKD, CURRENCY_USD, CURRENCY_CHF, CURRENCY_SEK,CURRENCY_DKK,
				CURRENCY_NOK,CURRENCY_JPY,CURRENCY_CAD,CURRENCY_AUD,CURRENCY_SGD,CURRENCY_EUR,CURRENCY_MOP,CURRENCY_THB};
		return currencys;
	}
}
