package com.kevin.service.ehcache.test;

import java.net.URI;

import org.ehcache.PersistentCacheManager;
import org.ehcache.clustered.client.config.builders.ClusteringServiceConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;

public class TerracottaTest {
	public static void main(String[] args) {
		CacheManagerBuilder<PersistentCacheManager> clusteredCacheManagerBuilder =
			    CacheManagerBuilder.newCacheManagerBuilder() 
			        .with(ClusteringServiceConfigurationBuilder.cluster(URI.create("terracotta://kevin1/default-server")) 
			            .autoCreate(c -> c)); 
			PersistentCacheManager cacheManager = clusteredCacheManagerBuilder.build(true); 

			cacheManager.close(); 
	}
}
