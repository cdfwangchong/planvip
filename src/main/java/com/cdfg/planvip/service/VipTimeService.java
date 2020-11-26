package com.cdfg.planvip.service;

import com.cdfg.planvip.pojo.dto.Viptime;

public interface VipTimeService {

    int insert(Viptime record);

    Viptime selectByPrimaryKey(String open_id);

}
