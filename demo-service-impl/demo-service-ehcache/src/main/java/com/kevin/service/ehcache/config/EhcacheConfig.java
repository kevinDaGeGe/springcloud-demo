package com.kevin.service.ehcache.config;

import java.sql.SQLException;
import java.time.Duration;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePool;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.CacheEventListener;
import org.ehcache.impl.config.BaseCacheConfiguration;
import org.ehcache.xml.model.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhcacheConfig {
	@Bean
	public ResourcePoolsBuilder masterSlaveDataSource() throws SQLException {
		long heapCacheSize = 1;
		long offHeapCacheSize =1;
		long diskCacheSize =1;
		ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.newResourcePoolsBuilder()
			    // 堆内缓存大小
			    .heap(heapCacheSize, MemoryUnit.MB)
			    // 堆外缓存大小
			    .offheap(offHeapCacheSize, MemoryUnit.MB)
			    // 文件缓存大小
			    .disk(diskCacheSize, MemoryUnit.MB);
		Class keyType;
		Class valueType;
		Duration withExpiry;
		long heapMaxObjectGraph;
		long heapMaxObjectSize;
		CacheConfigurationBuilder.newCacheConfigurationBuilder(keyType, valueType, resourcePoolsBuilder)
	    // 缓存超时时间
	    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(withExpiry))
	    // 统计对象大小时对象图遍历深度
	    .withSizeOfMaxObjectGraph(heapMaxObjectGraph)
	    //可缓存的最大对象大小
	    .withSizeOfMaxObjectSize(heapMaxObjectSize, MemoryUnit.MB)
	    // 添加监听器
	    .add(CacheEventListenerConfigurationBuilder.newEventListenerConfiguration(
	    		CacheEventListener.class, EventType.EXPIRED).unordered().asynchronous())
	    //最后调用build()返回一个完整的实例，但是该实例并未初始化。
	    .build();
		CacheConfiguration config = new BaseCacheConfiguration(valueType, valueType, null, null, null, null, null);
		CacheManagerBuilder.newCacheManagerBuilder().withCache("myCache", config ).build(true);
		return resourcePoolsBuilder;
	}
}
