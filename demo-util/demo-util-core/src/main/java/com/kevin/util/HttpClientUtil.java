/**   
 * Copyright © 2018 zlpay.
 */
package com.kevin.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @Description: 访问HTTP接口工具类
 * @author: zhangyq
 * @date: 2019-7-27 下午5:50:36  
 */
public class HttpClientUtil {
	public static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	/**
	 * 文件编码类型
	 */
	public final static String UTF8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";

	/**
	 * 连接超时时间
	 */
	private final static int CONNECT_TIMEOUT = 30000;
	/**
	 * 请求超时时间
	 */
	private final static int REQUEST_TIMEOUT = 50000;
	/**
	 * socket超时时间
	 */
	private final static int SOCKET_TIMEOUT = 50000;
	/**
	 * 请求格式
	 */
	private final static String FORMAT = "application/json";


	/**
	* @Description: post请求
	* @param url
	* @param jsonData
	* @return
	* @return String
	* @throws  
	* @author: zhangyq
	* @date: 2019-7-27 下午5:50:14 
	*/ 
	public static String doPost(String url, String jsonData) {
		// 创建可关闭client
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setRedirectsEnabled(true)
				.build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jsonData, ContentType.create(FORMAT, UTF8)));
			HttpResponse response = httpClient.execute(httpPost);
			log.info("[httpClientUtil_post]收到应答内容:{}!!", response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() == 200) {
				return String.valueOf(EntityUtils.toString(response.getEntity()));
			}
		} catch (Exception e) {
			log.error("[httpClientUtil_post]请求异常!!", e);
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("[httpClientUtil_post]流关闭异常!!", e);
				}
			}
		}
		return null;
	}
	/**
	* @Description: get请求 
	* @param url
	* @return
	* @return String
	* @throws  
	* @author: zhangyq
	* @date: 2019-7-27 下午5:50:03 
	*/ 
	public static String doGet(String url) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			log.info("[httpClientUtil_get]请求URL：" + url + ";code："+ code);
			if (code == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				return result;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}  

}
