package com.example.ssgg_oracle.mapper;

import com.example.ssgg_oracle.domain.citem_port;
import com.example.ssgg_oracle.domain.domain_test;

import java.util.List;


public interface mapper_test {
    List<domain_test> selectGoodsAwbList(domain_test reqDomain);

    List<domain_test> selectTempList();

    List<citem_port> selectTADM_PORT(String rgn_c);

}


