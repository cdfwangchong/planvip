package com.cdfg.planvip.service;

import com.cdfg.planvip.pojo.dto.Vipquestion;

import java.util.Date;
import java.util.List;

public interface VipquestionService {

    int insert(Vipquestion record);

    Vipquestion selectByPrimaryKey(String cardId);

    List<Vipquestion> select(String query_date);
}
