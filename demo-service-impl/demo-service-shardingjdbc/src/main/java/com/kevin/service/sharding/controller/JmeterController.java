package com.kevin.service.sharding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/jmeter")
@lombok.extern.slf4j.Slf4j
public class JmeterController {

	@org.springframework.beans.factory.annotation.Value("${server.sleep}")
	private long SLEEP_Time = 5l;

	int times;
	@RequestMapping("/iowait")
	public String iowait() throws InterruptedException {
		log.info("开始睡眠"+times++);
		long currentTimeMillis = System.currentTimeMillis();
		java.util.concurrent.TimeUnit.SECONDS.sleep(SLEEP_Time);
		/*int i=1000000000;
		int j = 0;9
		while((i-j)>0) {
			i--;
			j++;
		}*/
		long endcurrentTimeMillis = System.currentTimeMillis();

		log.info("结束睡眠"+(endcurrentTimeMillis-currentTimeMillis)+"毫秒");
		return "success";
	}
	@RequestMapping("/cpuwait")
	public String cpuwait() throws InterruptedException {
		log.info("开始睡眠"+times++);
		long currentTimeMillis = System.currentTimeMillis();
		int i=1000000000;
		int j = 0;
		while((i-j)>0) {
			i--;
			j++;
		}
		long endcurrentTimeMillis = System.currentTimeMillis();

		log.info("结束睡眠"+(endcurrentTimeMillis-currentTimeMillis)+"毫秒");
		return "success";
	}


}
