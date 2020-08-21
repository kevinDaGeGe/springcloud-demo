package com.kevin.service.dubbo.provider.service;

import com.kevin.api.dubbo.provider.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 生产者Dubbo接口实现
 * @Author Sans
 * @CreateTime 2019/11/6 23:01
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}