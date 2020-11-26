package com.cdfg.planvip.service;

import com.cdfg.planvip.pojo.dto.Vipuser;

import java.util.List;
import java.util.Map;

public interface VipuserService {

    int insert(Vipuser record);

    int insertSelective(List<Vipuser> record);

    Vipuser selectByPrimaryKey(String openId);

    Vipuser selectByPrimary(Map param);

    int updateByPrimaryKey(Map param);
}
