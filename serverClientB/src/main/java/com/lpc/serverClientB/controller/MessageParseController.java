package com.lpc.serverClientB.controller;

import com.lpc.serverClientB.dao.DemoFeignService;
import com.lpc.serverClientB.entity.OutputData;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageParseController {

    @Autowired
    private DemoFeignService demoFeignService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //初始页面index
    @RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
    public String index(ModelMap map) {

        OutputData output = new OutputData();
        output.setDate(getDate());
        map.put("fpMessage", output);
        return "index";
    }

    // 解析xml报文，并将解析后的xml赋值给output实体类，传给前端
    //@ResponseBody  //加上ResponseBody，会返回字符串
    @RequestMapping(value = "/parseXML", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    private String parse(ModelMap map) {

        OutputData outputData = new OutputData();

       // 服务降级处理
        String resultXML = demoFeignService.generate();


        if ("error".equals(resultXML)) {
            return "error";
        }

        //解析xml
        outputData = getXML(resultXML,outputData);
        map.put("fpMessage", outputData);
        return "index";
    }

    //解析xml报文
    private OutputData getXML(String xml,OutputData outputData) {

        try {
            Document document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();// 获取根节点FPXT

            Element inputElement = rootElement.element("OUTPUT");
            outputData.setFpzl(inputElement.element("FPZL").getTextTrim());
            outputData.setFpdm(inputElement.element("FPDM").getTextTrim());
            outputData.setFpzt(inputElement.element("FPZT").getTextTrim());
            outputData.setNsrsbh(inputElement.element("NSRSBH").getTextTrim());
            outputData.setDate(inputElement.element("DATE").getTextTrim());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception = " + e.toString());
        }
        return outputData;
    }

    //获取当前时间
    private String getDate() {

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return simpleDate.format(date);
    }
}