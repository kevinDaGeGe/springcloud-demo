package com.kevin.common.txmanage.service;

import org.springframework.stereotype.Service;

@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {

	@Value("${tm.manager.url}")
	private String url;

	@Override
	public String getTxUrl() {
		System.out.println("load tm.manager.url ");
		return url;
	}
}
