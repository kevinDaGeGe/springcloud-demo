package com.kevin.common.txmanage.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpUtils;

@Service
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService {

	@Override
	public String httpGet(String url) {
		System.out.println("httpGet-start");
		String res = HttpUtils.get(url);
		System.out.println("httpGet-end");
		return res;
	}

	@Override
	public String httpPost(String url, String params) {
		System.out.println("httpPost-start");
		String res = HttpUtils.post(url, params);
		System.out.println("httpPost-end");
		return res;
	}
}

