package com.example.ssgg_oracle.service;

import com.example.ssgg_oracle.domain.citem_port;
import com.example.ssgg_oracle.domain.domain_test;
import com.example.ssgg_oracle.mapper.mapper_test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTestImpl implements ServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ServiceTestImpl.class);

    @Autowired
    mapper_test daoMapper;

    @Value("${spring.profiles.active}")
    private String envValue;

    @Override
    public String bcApiGoods() {
        String strSuccess = "FALSE";
        //1. 전송대상 awb List
        List<domain_test> listAwbs = new ArrayList<domain_test>();
        try {
            domain_test req = new domain_test();
//            req.setEnv_value(envValue); // DEV, STG, PRD
            listAwbs = daoMapper.selectGoodsAwbList(req); // target awbs

            strSuccess = "TRUE";
        } catch (Exception e) {
//            logger.error(e.toString());
        }

//        for (domain_test reqDomain : listAwbs) {
//            try {
//                JSONObject objM = new JSONObject();
//                List<domain_test> listAwbInfo = new ArrayList<domain_test>();
//                listAwbInfo = daoMapper.selectGoodsAwbInfo(reqDomain);
//                for (domain_test reqDomainAwb : listAwbInfo) {
//                    objM.put("AWBNo", reqDomainAwb.getAwbno().toString());
//                    objM.put("AllPtRcv", reqDomainAwb.getAllptrcv().toString());
//                    objM.put("ScrSts", reqDomainAwb.getScrstatus().toString());
//                    objM.put("SecRvw", reqDomainAwb.getSecrvw().toString());
//                }
//                if (objM.get("AWBNo") == null) {
//                    objM.put("AWBNo", reqDomain.getAwbno().toString());
//                    objM.put("AllPtRcv", "");
//                    objM.put("ScrSts", "");
//                    objM.put("SecRvw", "");
//                }
//                objM.put("LooseAcpt", arrayLooseAcpt(reqDomain));
//                objM.put("ULDAcpt", arrayUldAcpt(reqDomain));
//                objM.put("Dim", arrayDim(reqDomain));
//
//                // System.out.println("############ objM.toString() #################");
//                System.out.println(reqDomain.getAwbno() + ":" + objM.toString());
//                if (bcWsCall(strUrl, objM.toString())) {  // 웹서비스 호출
//                    reqDomain.setJsonmsg(objM.toString());
//                    updateSuccessGoods(reqDomain);
//                    strSuccess = "TRUE";
//                } else {
//                    reqDomain.setJsonmsg(objM.toString());
//                    updateFailGoods(reqDomain);
//                    strSuccess = "FALSE";
//                    System.out.println("AWB ERROR: " + reqDomain.getAwbno().toString());
//                    // bj.stopScheduler(); // 오류시 스케쥴 종료
//                }
//            } catch (Exception e) {
//                updateFailGoods(reqDomain);
//                strSuccess = "FALSE";
//                logger.error(e.toString());
//                System.out.println("Web Service Call Fail");
//            }
//        }
        return strSuccess;
    }

    @Override
    public String bcJsonRtn1(){
        String strSuccess = "FALSE";
        List<domain_test> result = new ArrayList<domain_test>();

        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            domain_test req = new domain_test();
            req.setAwbno("parm1");
            result = daoMapper.selectTempList();
        }catch(Exception e) {
            logger.error(e.toString());
        }

        // db 연결된 결과값 가지고 json 포멧 만들기
        JSONObject objM = new JSONObject();
        for(domain_test reqDomain : result) {
            try {
                objM.put("AWBNo" , reqDomain.getAwbno().toString());
                objM.put("Orgn"  , reqDomain.getStn());
                objM.put("AWBNo1" , "awb");
                objM.put("Orgn2"  , "orgn");
                objM.put("Dest"  , "dest");
                objM.put("PC"    , "pc");
                objM.put("WT"    , "wt");
                System.out.println(reqDomain.getAwbno()+":"+ objM.toString());
            }catch(Exception e) {
                strSuccess="FALSE";
                logger.error(e.toString());
                System.out.println("Web Service Call Fail");
            }
        }

        return objM.toString();
    }

    @Override
    public JSONArray bcJsonRtn2(String rgn_c){
        String strSuccess = "FALSE";
        List<citem_port> result = new ArrayList<citem_port>();

        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            citem_port req = new citem_port();
            req.setIata_port_c("ICN");
            result = daoMapper.selectTADM_PORT(rgn_c);

            System.out.println("LOGGING..1");
        }catch(Exception e) {
            logger.error(e.toString());
        }

        // db 연결된 결과값 가지고 json 포멧 만들기
        JSONArray arrayAcpt = new JSONArray();
        try {
            for(citem_port reqDomain : result) {
                JSONObject objLAcpt = new JSONObject();
                objLAcpt.put("IATA_PORT_C" , reqDomain.getIata_port_c());
                objLAcpt.put("PORT_N"  , reqDomain.getPort_n());
                arrayAcpt.add(objLAcpt);
            }
        }catch(Exception e) {
            logger.error(e.toString());
        }

        System.out.println("obj:"+ arrayAcpt.toString());

        return arrayAcpt;
    }


}
