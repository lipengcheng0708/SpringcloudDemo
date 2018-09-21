package com.lpc.serverClientB.dao;

import com.lpc.serverClientB.dao.fallback.DemoFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client-serverA", fallback = DemoFeignFallback.class)
public interface DemoFeignService {

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    String generate();
}