package com.lpc.serverClientB.dao.fallback;


import com.lpc.serverClientB.dao.DemoFeignService;
import org.springframework.stereotype.Component;

@Component
public class DemoFeignFallback implements DemoFeignService {

	//熔断
	@Override
	public String generate() {
		return "";
	}
}
