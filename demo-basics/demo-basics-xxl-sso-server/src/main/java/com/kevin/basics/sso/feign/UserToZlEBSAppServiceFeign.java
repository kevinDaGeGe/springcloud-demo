package com.kevin.basics.sso.feign;

import com.kevin.service.api.mycat.app.UserToZlEBSAppService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("demo-service-mycat")
public interface UserToZlEBSAppServiceFeign extends UserToZlEBSAppService {

}
