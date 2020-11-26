package com.cdfg.planvip.dao;

import com.cdfg.planvip.pojo.dto.Viptime;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ViptimeDao {

    int insert(Viptime record);

    int insertSelective(Viptime record);

    Viptime selectByPrimaryKey(Map param);

    int selectsd(Map param);

    int selectrs(Map param);

    int updateByPrimaryKeySelective(Viptime record);

    int updateByPrimaryKey(Viptime record);
}