package com.cdfg.planvip.service.impl;

import com.cdfg.planvip.dao.VipuserDao;
import com.cdfg.planvip.pojo.dto.Vipuser;
import com.cdfg.planvip.service.VipuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VipuserServiceImpl implements VipuserService {
    @Autowired
    VipuserDao vuDao = null;

    @Override
    public int insert(Vipuser record) {
        return 0;
    }

    @Override
    public int insertSelective(List<Vipuser> record) {

        try {
            vuDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Vipuser selectByPrimaryKey(String openId) {
        Vipuser vipuser = vuDao.selectByPrimaryKey(openId);
        return vipuser;
    }

    @Override
    public Vipuser selectByPrimary(Map param) {
        Vipuser vipuser = vuDao.selectByPrimary(param);
        return vipuser;
    }

    @Override
    public int updateByPrimaryKey(Map param) {
        int ret = vuDao.updateByPrimaryKey(param);
        return ret;
    }
}
