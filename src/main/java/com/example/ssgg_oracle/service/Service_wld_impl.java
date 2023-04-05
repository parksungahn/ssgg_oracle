package com.example.ssgg_oracle.service;

import com.example.ssgg_oracle.domain.domain_carr;
import com.example.ssgg_oracle.domain.domain_port;
import com.example.ssgg_oracle.mapper.mapper_wld;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class Service_wld_impl implements Service_wld {
    private static final Logger logger = LoggerFactory.getLogger(ServiceTestImpl.class);

    @Autowired
    mapper_wld daoMapper;

    @Override
    public JSONArray SearchTest(){
        String strSuccess = "FALSE";
        List<domain_port> result = new ArrayList<domain_port>();

        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            domain_port req = new domain_port();
            result = daoMapper.mapper_selectTest();
        }catch(Exception e) {
            logger.error(e.toString());
        }

        // db 연결된 결과값 가지고 json 포멧 만들기
        JSONArray arrayAcpt = new JSONArray();
        try {
            for(domain_port reqDomain : result) {
                JSONObject objLAcpt = new JSONObject();
                objLAcpt.put("port_c" , reqDomain.getPort_c());
                objLAcpt.put("port_name"  , reqDomain.getPort_name());
                objLAcpt.put("port_desc"  , reqDomain.getPort_desc());
                arrayAcpt.add(objLAcpt);
            }
        }catch(Exception e) {
            logger.error(e.toString());
        }

        System.out.println("obj:"+ arrayAcpt.toString());

        return arrayAcpt;
    }


    @Override
    public JSONArray SearchPort(String port_c){
        String strSuccess = "FALSE";
        List<domain_port> result = new ArrayList<domain_port>();

        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            domain_port req = new domain_port();
//            req.setPort_c("ICN");
            result = daoMapper.mapper_selectTPort(port_c);
        }catch(Exception e) {
            logger.error(e.toString());
        }

        // db 연결된 결과값 가지고 json 포멧 만들기
        JSONArray arrayAcpt = new JSONArray();
        try {
            for(domain_port reqDomain : result) {
                JSONObject objLAcpt = new JSONObject();
                objLAcpt.put("port_c" , reqDomain.getPort_c());
                objLAcpt.put("port_name"  , reqDomain.getPort_name());
                objLAcpt.put("port_desc"  , reqDomain.getPort_desc());
                arrayAcpt.add(objLAcpt);
            }
        }catch(Exception e) {
            logger.error(e.toString());
        }

//        System.out.println("obj:"+ arrayAcpt.toString());

        return arrayAcpt;
    }

    @Override
    public JSONArray SearchCarr(String carr_c)
    {
        String strSuccess = "FALSE";
        List<domain_carr> result = new ArrayList<domain_carr>();

        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            domain_carr req = new domain_carr();
//            req.setPort_c("ICN");
            result = daoMapper.mapper_selectCarr(carr_c);
        }catch(Exception e) {
            logger.error(e.toString());
        }

        // db 연결된 결과값 가지고 json 포멧 만들기
        JSONArray arrayAcpt = new JSONArray();
        try {
            for(domain_carr reqDomain : result) {
                JSONObject objLAcpt = new JSONObject();
                objLAcpt.put("carr_c" , reqDomain.getCARR_C());
                objLAcpt.put("desc_e"  , reqDomain.getDESC_E());
                arrayAcpt.add(objLAcpt);
            }
        }catch(Exception e) {
            logger.error(e.toString());
        }

//        System.out.println("obj:"+ arrayAcpt.toString());

        return arrayAcpt;
    }


    @Override
    public String SavePort(String port_c, String port_name, String port_desc){
        String bResult = "OK";
        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            daoMapper.mapper_SavePort(port_c, port_name, port_desc);
        }catch(Exception e) {
            logger.error(e.toString());
            bResult = e.toString();
        }

        return bResult;
    }

    @Override
    public boolean UpdatePort(String port_c, String port_name, String port_desc){
        boolean bOk = true;
        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            daoMapper.mapper_UpdatePort(port_c, port_name, port_desc);
        }catch(Exception e) {
            logger.error(e.toString());
            bOk = false;
        }

        return bOk;
    }

    @Override
    public boolean DeletePort(String port_c){
        boolean bOk = true;
        // db 연결 및 리턴값 list 받아오기 호출부
        try {
            daoMapper.mapper_DeletePort(port_c);
        }catch(Exception e) {
            logger.error(e.toString());
            bOk = false;
        }

        return bOk;
    }

}
