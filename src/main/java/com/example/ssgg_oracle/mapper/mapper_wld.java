package com.example.ssgg_oracle.mapper;

import com.example.ssgg_oracle.domain.domain_carr;
import com.example.ssgg_oracle.domain.domain_port;

import java.util.List;

public interface mapper_wld {

    List<domain_port> mapper_selectTest();
    List<domain_port> mapper_selectTPort(String port_c);
    List<domain_carr> mapper_selectCarr(String carr_c);

    boolean mapper_SavePort(String port_c, String port_name, String port_desc);
    boolean mapper_UpdatePort(String port_c, String port_name, String port_desc);
    boolean mapper_DeletePort(String port_c);
}
