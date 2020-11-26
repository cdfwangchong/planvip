package com.cdfg.planvip.service.impl;

import cn.cdfg.exceptionHandle.PlanVipNotFoundException;
import com.cdfg.planvip.dao.ViptimeDao;
import com.cdfg.planvip.dao.VipuserDao;
import com.cdfg.planvip.pojo.dto.Viptime;
import com.cdfg.planvip.pojo.dto.Vipuser;
import com.cdfg.planvip.service.VipTimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cdfg.planvip.pojo.utill.Constant.*;

@Service
public class VipTimeServiceImpl implements VipTimeService {
    @Autowired
    ViptimeDao viptimedao = null;

    @Autowired
    VipuserDao vuDao = null;

    Logger logger = Logger.getLogger(VipquestionServiceImpl.class);
    @Override
    public int insert(Viptime record) {
        Date pretime = record.getPreTime();
        String preyysd = record.getPreYysd();
        Date vipTime = record.getVipTime();
        String vipYysd = record.getVipYysd();
        //预约日期和时段作为参数
        Map param = new HashMap();
        param.put("vipTime",vipTime);
        param.put("vipYysd",vipYysd);

        //判断该时段是否被暂用，当天是否满5个人
        int countsd = 0;
        int countrs = 0;
        try {
            countsd = viptimedao.selectsd(param);

            countrs = viptimedao.selectrs(param);
        } catch (Exception e) {
            logger.error("查询当天预约人数失败");
            throw new PlanVipNotFoundException(errCode14,errMsg14);
        }

        if(countsd > 0) {
            logger.error("该时段已被预约");
            throw new PlanVipNotFoundException(errCode12,errMsg12);
        }
        if (countrs > 5) {
            logger.error("该日期预约人数已满");
            throw new PlanVipNotFoundException(errCode13,errMsg13);
        }

        String openId = record.getOpen_id();
        //得到openid
        Vipuser vu;
        try {
            vu = vuDao.selectByPrimaryKey(openId);
        } catch (Exception e) {
            logger.error("vip用户表查询异常");
            throw new PlanVipNotFoundException(errCode10,errMsg10);
        }
        record.setTelNum(vu.getTelNum());
        record.setVipName(vu.getVipName());
        record.setCardId(vu.getCardId());

        //判断有没有原预约日期和时段
        if (preyysd.isEmpty() || pretime == null) {
            viptimedao.insert(record);
        }else {
            viptimedao.updateByPrimaryKey(record);
        }

        return 0;
    }

    @Override
    public Viptime selectByPrimaryKey(String openId) {
        //得到openid
        Vipuser vu;
        try {
            vu = vuDao.selectByPrimaryKey(openId);
        } catch (Exception e) {
            logger.error("vip用户表查询异常");
            throw new PlanVipNotFoundException(errCode10,errMsg10);
        }
        Map param = new HashMap();
        param.put("telNum",vu.getTelNum());
        param.put("cardId",vu.getCardId());

        Viptime vt;
        try {
            vt = viptimedao.selectByPrimaryKey(param);
        } catch (Exception e) {
            logger.error("预约时间查询异常");
            throw new PlanVipNotFoundException(errCode15,errMsg15);
        }

        return vt;
    }

}
