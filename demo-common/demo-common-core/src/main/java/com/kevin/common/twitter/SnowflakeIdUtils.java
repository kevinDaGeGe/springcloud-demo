package com.kevin.common.twitter;

/***
* @Description: 使用雪花算法生成全局id
* @throws  
* @author: yu.han
* @date: 2020/8/11 14:54
*/
public class SnowflakeIdUtils {
	private static SnowflakeIdWorker idWorker;
	static {
		// 使用静态代码块初始化 SnowflakeIdWorker
		idWorker = new SnowflakeIdWorker(1, 1);
	}

	public static String nextId() {
		return idWorker.nextId() + "";
	}

}
