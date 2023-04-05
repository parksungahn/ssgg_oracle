package com.example.ssgg_oracle.service;

import org.json.simple.JSONArray;

public interface Service_wld {

    JSONArray SearchTest();
    JSONArray SearchPort(String port_c);
    JSONArray SearchCarr(String carr_c);

    //--save
    String SavePort(String port_c, String port_name, String port_desc);
    boolean UpdatePort(String port_c, String port_name, String port_desc);
    boolean DeletePort(String port_c);

}
