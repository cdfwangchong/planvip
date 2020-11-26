package com.cdfg.planvip.dao;

import com.cdfg.planvip.pojo.dto.Vipuser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VipuserDao {

    int insertSelective(List<Vipuser> record);

    Vipuser selectByPrimaryKey(String openId);

    Vipuser selectByPrimary(Map param);

    int updateByPrimaryKey(Map param);

}