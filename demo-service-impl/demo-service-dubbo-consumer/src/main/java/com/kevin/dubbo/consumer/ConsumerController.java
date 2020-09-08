package com.kevin.dubbo.consumer;

import com.kevin.api.dubbo.provider.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费测试接口
 * @Description: 
 * @author: 67017
 * @date: 2020年8月31日 上午10:27:36
 */
@RestController
public class ConsumerController {
    // Dubbo远程调用注解
    @Reference
    private DemoService service;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getList(){
        // 远程调用
        String message = service.sayHello("dubbo");
        System.out.println(message);
        return message;
    }

}