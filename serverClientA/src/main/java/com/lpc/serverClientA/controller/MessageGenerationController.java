package com.lpc.serverClientA.controller;

import com.lpc.serverClientA.entity.InputData;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageGenerationController {

    private String result, time;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
    public String index(ModelMap map) {

        InputData inputData = new InputData();
        time = getDate();

        inputData.setDate(time);
        map.put("fpInfo", inputData);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/generateXML", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    private String generate(@ModelAttribute InputData inputData) {

        logger.debug("inputData");
        logger.debug("inputData = " + inputData.toString());
        result = rtnXML(inputData);
        return "result = " + result;
    }

    //生成xml报文
    private String rtnXML(InputData inputData) {

        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("utf-8");
        Element root = document.addElement("FPXT");//添加根节点
        Element output = root.addElement("OUTPUT");

        Element fpzl = output.addElement("FPZL");
        fpzl.addText(inputData.getFpzl());

        Element fpzt = output.addElement("FPZT");
        fpzt.addText(inputData.getFpzt());

        Element nsrsbh = output.addElement("NSRSBH");
        nsrsbh.addText(inputData.getNsrsbh());

        Element date = output.addElement("DATE");
        date.addText(time);

        Element fpdm = output.addElement("FPDM");
        fpdm.addText(inputData.getFpdm());

        String resultXML = document.asXML();
        logger.debug("resultXML = " + resultXML);
        return resultXML;
    }

    private String getDate() {

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return simpleDate.format(date);
    }


    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity<Object> generate() {

        logger.debug("resultXML = " + result);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
