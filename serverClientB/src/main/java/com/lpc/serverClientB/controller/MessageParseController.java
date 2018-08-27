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
    private String resultXML;

    //初始页面index
    @RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
    public String index(ModelMap map) {

        OutputData outputData = new OutputData();
        outputData.setDate(getDate());
        map.put("fpMessage", outputData);
        return "index";
    }

    // 解析xml报文，并将解析后的xml赋值给output实体类，传给前端
    //@ResponseBody  //加上ResponseBody，会返回字符串
    @RequestMapping(value = "/parseXML", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    private String parse(ModelMap map) {

        resultXML = demoFeignService.generate();
        if ("".equals(resultXML)) {
            return "error";
        }

        //解析xml
        OutputData outputData = getXML(resultXML);
        map.put("fpMessage", outputData);
        return "index";
    }

    //解析xml报文
    private OutputData getXML(String xml) {

        OutputData outputData = new OutputData();
        try {
            Document document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();//获取跟节点FPXT

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

    private String getDate() {

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return simpleDate.format(date);
    }

    //接收serviceA的xml报文
    @RequestMapping(value = "/serviceGet", method = RequestMethod.GET)
    public void helloService() {

        resultXML = demoFeignService.generate();
        logger.debug("接收serviceA的xml报文 = "+resultXML);
    }
}
