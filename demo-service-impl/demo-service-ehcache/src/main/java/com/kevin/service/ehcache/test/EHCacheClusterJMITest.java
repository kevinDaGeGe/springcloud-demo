package com.kevin.service.ehcache.test;
import java.io.InputStream;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.management.ManagementService;

public class EHCacheClusterJMITest {

	public static void main(String[] args) {
		InputStream is = EHCacheClusterJMITest.class
				.getResourceAsStream("E:\\software\\project\\test\\springcloud-demo\\demo-service-impl\\demo-service-ehcache\\src\\main\\resources\\config\\ehcache.xml");
		//读入配置
		CacheManager cacheManager = new CacheManager(is);
		//打印初始缓存
		String[] cacheNames = cacheManager.getCacheNames();
		printNames(cacheNames);
		//移除缓存
		cacheManager.removeCache("myCache");
		cacheNames = cacheManager.getCacheNames();
		printNames(cacheNames);

		//新建缓存
		Cache cache = new Cache("myCache", 100, true, false, 10, 10);
		cacheManager.addCache(cache);
		cacheNames = cacheManager.getCacheNames();
		printNames(cacheNames);
		cache.put(new Element("test1", "value1"));
		
		//得到缓存并插入值（这里监听器被调用）
		cache = cacheManager.getCache("myCache");
		for (int i = 0; i < 20; i++) {
			cache.put(new Element("key" + i, "value" + i));
		}
		cache.get("key10");
		
		// distributed -- rmi同步
		cache = cacheManager.getCache("myCache");
		for (int i = 0; i < 100; i++) {
			cache.put(new Element("key" + i , "value" + i));
		}
		
		//注册被管理的Bean
		// JMX -- jconsole(MBeanServer)
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ManagementService.registerMBeans(cacheManager, mBeanServer, true, true,
				true, true);

		for (int i = 0; i < 10; i++) {
			Element temp = cache.get("myCache");
			if (temp != null) {
				System.out.println(temp.getValue());
			} else {
				System.out.println("NotFound");
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// distributed cache using RMI
		// 1.Peer Discovery -- cacheManagerPeerProviderFactory
		// 2.CacheManager -- cacheManagerPeerListenerFactory
		// 3.cache replication -- cacheEventListenerFactory
		// 4.Bootstrap -- 启动后同步
	}

	private static void printNames(String[] names) {
		System.out.println("=======================");
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

}