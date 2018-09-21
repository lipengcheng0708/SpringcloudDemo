package com.lpc.serverClientB.dao.fallback;

import com.lpc.serverClientB.dao.DemoFeignService;
import org.springframework.stereotype.Component;

@Component
public class DemoFeignFallback implements DemoFeignService {

	//服务降级
	@Override
	public String generate() {
		return "error";
	}
}
