package com.kevin.service.dubbo.provider;

import com.kevin.api.dubbo.provider.service.DemoService;
import com.kevin.service.dubbo.provider.service.DemoServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;


public class ProducerZookeeperApplication {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "kevin2");

    public static void main(String[] args) throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
