package com.example.ssgg_oracle.controller;

import com.example.ssgg_oracle.service.ServiceTest;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ssgg_oracle.mapper.mapper_test;
import com.example.ssgg_oracle.domain.domain_test;

import java.util.ArrayList;
import java.util.List;

@RestController
public class controller_test {
//    @Value("${spring.profiles.active}")
//    private String envValue;

//    @Value("${bcApiServiceUrl}")
//    private String envBcApiServiceUrl;
//
    @Autowired
    mapper_test daoMapper;

    @Autowired
    ServiceTest bcService;

    //--http://localhost:8080/test/hello
    @GetMapping("/test/hello")
    public String test1() {
        return "Hello world";
    }

    @RequestMapping(value = "/bcApiGoods.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String bcApiGoods(Model model) {
        String rtn = bcService.bcApiGoods();
        return rtn;
    }

    //mapper 다이렉트 호출샘플
    @RequestMapping(value = "/testCall.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String testCall(Model model) {
        List<domain_test> list = new ArrayList<domain_test>();
        list = daoMapper.selectTempList();
        return "ok";
    }

    //controller>service>mapper 호출 샘플
    @RequestMapping(value = "/testCall2.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String testCall2(Model model) {
        String jsonRtn = bcService.bcJsonRtn1();
        return jsonRtn;
    }

    //--http://localhost:8080/testCall3.do/1            (PathVariable)
    @RequestMapping(value = "/testCall3.do/{rgn_c}", method = {RequestMethod.GET, RequestMethod.POST})
    public String testCall3(@PathVariable("rgn_c") String rgn_c) {
        JSONArray jsonRtn = bcService.bcJsonRtn2(rgn_c);
//        System.out.println("javac:"+ jsonRtn);
        return jsonRtn.toJSONString();
    }

    //--POST 호출 => http://localhost:8080/testCall4.do?rgn_c=3  OR API호출.PARAM추가후 호출     (RequestParam)
    @PostMapping("/testCall4.do")
    public String testCall4(@RequestParam("rgn_c") String rgn_c) {
        JSONArray jsonRtn = bcService.bcJsonRtn2(rgn_c);
//        System.out.println("javac:"+ jsonRtn);
        return jsonRtn.toJSONString();
    }

}
