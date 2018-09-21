package com.lpc.eurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//记得添加该注解
@Component
public class IPFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        // 可以在请求被路由之前调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    // test方法 获取ip地址

    @Override
    public Object run() {
        //filter需要执行的具体操作
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String ip = getIPaddress(request);
        logger.info("ip == " + ip);//ip == 0:0:0:0:0:0:0:1

        // 配置本地IP白名单，生产环境可放入数据库或者redis中
        List<String> list = new ArrayList<>();
        list.add("172.0.0.1");
        list.add("127.0.0.1");
        //list.add("0:0:0:0:0:0:0:1");

        if (!list.contains(ip)) {
            logger.info("IP地址校验不通过！");
            requestContext.setResponseStatusCode(401);
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("IP is forbidden!");
        } else {
            logger.info("IP地址校验通过。");
        }
        return null;
    }


    //test测试


    private String getIPaddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
