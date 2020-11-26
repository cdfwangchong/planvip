package com.cdfg.planvip.dao;

import com.cdfg.planvip.pojo.dto.Vipquestion;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VipquestionDao {
    int insert(Vipquestion record);

    Vipquestion selectByPrimaryKey(String openId);

    List<Vipquestion> select(String query_date);
}