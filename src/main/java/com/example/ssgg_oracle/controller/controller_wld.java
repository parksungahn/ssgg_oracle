package com.example.ssgg_oracle.controller;

import com.example.ssgg_oracle.service.Service_wld;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class controller_wld {

    //--안드로이드 에서 호출(servicekey 필요)
    @Autowired
    Service_wld svcWld;

    @Value("${servicekey}")
    private String servicekey;

    //--GET 호출 => http://localhost:8080/port_test
    @GetMapping("/port_test")
    public String Port()
    {
        JSONArray jsonRtn = svcWld.SearchTest();
//        System.out.println("javac:"+ jsonRtn);
        return jsonRtn.toJSONString();
    }

    //--GET 호출 => http://localhost:8080/port?port_c=CJU
    @GetMapping("/port")
    public String Port(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c)
    {
        //--#################################################### Check Key
//        String pServiceKey = header.get("servicekey").toString();
//        String sServicekey = "[" + servicekey + "]";

//        if(pServiceKey.equals(sServicekey)==true)
//        {
//            System.out.println("Auth successful !!");
//        }
//        else
//        {
//            System.out.println("servicekey error !!");
//
//            JSONObject objM = new JSONObject();
//            objM.put("code" , "500");   //-- 응답
//            objM.put("message"  , "servicekey error");
//            return objM.toJSONString();
//        }
        //--#################################################### Check Key

        JSONArray jsonRtn = svcWld.SearchPort(port_c);
        System.out.println("javac:"+ port_c + ":" + jsonRtn);
        return jsonRtn.toJSONString();
    }

    //--GET 호출 => http://localhost:8080/carr?carr_c=KE
    @GetMapping("/carr")
    public String ARLN_CD(@RequestHeader HttpHeaders header, @RequestParam("carr_c") String carr_c)
    {
        //--#################################################### Check Key
//        String pServiceKey = header.get("servicekey").toString();
//        String sServicekey = "[" + servicekey + "]";

//        if(pServiceKey.equals(sServicekey)==true)
//        {
//            System.out.println("Auth successful !!");
//        }
//        else
//        {
//            System.out.println("servicekey error !!");
//
//            JSONObject objM = new JSONObject();
//            objM.put("code" , "500");   //-- 응답
//            objM.put("message"  , "servicekey error");
//            return objM.toJSONString();
//        }
        //--#################################################### Check Key

        JSONArray jsonRtn = svcWld.SearchCarr(carr_c);
        System.out.println("javac2:"+ carr_c + ":" + jsonRtn);
        return jsonRtn.toJSONString();
    }

    //--GET 호출 => http://localhost:8080/port2?port_c=CJU
    @GetMapping("/port2")
    public ResponseEntity<String> Port22(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c)
    {
        //--#################################################### Check Key
//        String pServiceKey = header.get("servicekey").toString();
//        String sServicekey = "[" + servicekey + "]";
//
//        if(pServiceKey.equals(sServicekey)==true)
//        {
//            System.out.println("Auth successful !!");
//        }
//        else
//        {
//            System.out.println("servicekey error !!");
//
//            JSONObject objM = new JSONObject();
//            objM.put("code" , "500");   //-- 응답
//            objM.put("message"  , "servicekey error");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicekey error");
//        }
        //--#################################################### Check Key

        JSONArray jsonRtn = svcWld.SearchPort(port_c);
//        System.out.println("javac:"+ port_c + ":" + jsonRtn);
//        return jsonRtn.toJSONString();
        return ResponseEntity.status(HttpStatus.OK).body(jsonRtn.toJSONString());
    }

    //--일반 json 형태의 결과리턴.
    @PostMapping("/port")
    public String Port2(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c, @RequestParam("port_name") String port_name, @RequestParam("port_desc") String port_desc)
    {
        //--HEADER 가져오기
        System.out.println("HEADER :"+ header); //--전체
        System.out.println("HEADER :"+ header.get("servicekey"));
        String pServiceKey = header.get("servicekey").toString();
        String sServicekey = "[" + servicekey + "]";

        if(pServiceKey.equals(sServicekey)==true)
        {
            System.out.println("Auth successful !!");
        }
        else
        {
            System.out.println("servicekey error !!");

            JSONObject objM = new JSONObject();
            objM.put("code" , "500");   //-- 응답
            objM.put("message"  , "servicekey error");
            return objM.toJSONString();
        }

        String bResult = svcWld.SavePort(port_c, port_name, port_desc);
//        System.out.println("bOk :"+ bResult);

        if("OK".equalsIgnoreCase(bResult)==true)
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "200");   //--일반성공 응답
//            objM.put("code" , "201");   //--CREATE 응답
            objM.put("message"  , "successful");
//            return ResponseEntity.status(HttpStatus.OK).body(objM.toJSONString());
            return objM.toJSONString();
        }
        else
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "500");
            objM.put("message"  , bResult);
            return objM.toJSONString();
        }
    }

    //--POST Save 호출 => http://localhost:8080/port
    //--ResponseEntity<String>  / 성공시 Talented API TESTER 에선.201 리턴받음, 안드로이드는 못받음.(성공시 INTERNAL_SERVER_ERROR 넘기면 리턴받음)
    @PostMapping("/port_save2")
    public ResponseEntity<String> port_save2(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c, @RequestParam("port_name") String port_name, @RequestParam("port_desc") String port_desc)
    {
        //--#################################################### Check Key
        String pServiceKey = header.get("servicekey").toString();
        String sServicekey = "[" + servicekey + "]";

        if(pServiceKey.equals(sServicekey)==true)
        {
            System.out.println("Auth successful !!");
        }
        else
        {
            System.out.println("servicekey error !!");

            JSONObject objM = new JSONObject();
            objM.put("code" , "500");   //-- 응답
            objM.put("message"  , "servicekey error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicekey error");
        }
        //--#################################################### Check Key


        String bResult = svcWld.SavePort(port_c, port_name, port_desc);
        System.out.println("bOk :"+ bResult);

        if("OK".equalsIgnoreCase(bResult)==true)
        {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bResult);     //--201 INSERT, CREATE 응답코드
//            return ResponseEntity.status(HttpStatus.CREATED).body(bResult);     //--201 INSERT, CREATE 응답코드
            return ResponseEntity.status(HttpStatus.OK).body(bResult);        //--200 일반성공 코드
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bResult);
        }
    }

    //--PUT UPDATE 호출 => http://localhost:8080/port
    @PutMapping("/port")
    public String Port3(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c, @RequestParam("port_name") String port_name, @RequestParam("port_desc") String port_desc)
    {
        //--#################################################### Check Key
        String pServiceKey = header.get("servicekey").toString();
        String sServicekey = "[" + servicekey + "]";

        if(pServiceKey.equals(sServicekey)==true)
        {
            System.out.println("Auth successful !!");
        }
        else
        {
            System.out.println("servicekey error !!");

            JSONObject objM = new JSONObject();
            objM.put("code" , "500");   //-- 응답
            objM.put("message"  , "servicekey error");
            return objM.toJSONString();
        }
        //--#################################################### Check Key

        boolean bOk = svcWld.UpdatePort(port_c, port_name, port_desc);
        if(bOk==true)
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "200");   //--일반성공 응답
            objM.put("message"  , "successful");
//            return ResponseEntity.status(HttpStatus.OK).body(objM.toJSONString());
            return objM.toJSONString();
        }
        else
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "500");
            objM.put("message"  , "failed");
            return objM.toJSONString();
        }
    }

    //--DELETE delete호출 => http://localhost:8080/port
    @DeleteMapping("/port")
    public String Port4(@RequestHeader HttpHeaders header, @RequestParam("port_c") String port_c)
    {
        //--#################################################### Check Key
        String pServiceKey = header.get("servicekey").toString();
        String sServicekey = "[" + servicekey + "]";

        if(pServiceKey.equals(sServicekey)==true)
        {
            System.out.println("Auth successful !!");
        }
        else
        {
            System.out.println("servicekey error !!");

            JSONObject objM = new JSONObject();
            objM.put("code" , "500");   //-- 응답
            objM.put("message"  , "servicekey error");
            return objM.toJSONString();
        }
        //--#################################################### Check Key

        boolean bOk = svcWld.DeletePort(port_c);
        if(bOk==true)
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "200");   //--일반성공 응답
            objM.put("message"  , "successful");
//            return ResponseEntity.status(HttpStatus.OK).body(objM.toJSONString());
            return objM.toJSONString();
        }
        else
        {
            JSONObject objM = new JSONObject();
            objM.put("code" , "500");
            objM.put("message"  , "failed");
            return objM.toJSONString();
        }
    }
}
