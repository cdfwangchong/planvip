package com.cdfg.planvip.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.PlanVipNotFoundException;
import com.cdfg.planvip.dao.VipquestionDao;
import com.cdfg.planvip.dao.VipuserDao;
import com.cdfg.planvip.pojo.dto.Vipquestion;
import com.cdfg.planvip.pojo.dto.Vipuser;
import com.cdfg.planvip.service.VipquestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.cdfg.planvip.pojo.utill.Constant.*;

@Service
public class VipquestionServiceImpl implements VipquestionService {
    @Autowired
    VipquestionDao vqDao= null;

    @Autowired
    VipuserDao vuDao = null;

    Logger logger = Logger.getLogger(VipquestionServiceImpl.class);

    /**
     * 写入调查问卷表，如果有则更新
     * @param record
     * @return
     */
    @Override
    public int insert(Vipquestion record) {
        String openId = record.getOpen_id();
        Vipuser vu;
        try {
            vu = vuDao.selectByPrimaryKey(openId);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("vip用户表查询异常");
            throw new PlanVipNotFoundException(errCode10,errMsg10);
        }
        if (vu == null) {
            logger.error("获取到的vip用户对象值为空");
            throw new PlanVipNotFoundException(errCode7,errMsg7);
        }

        record.setCardId(vu.getCardId());
        record.setTelNum(vu.getTelNum());
        record.setVipName(vu.getVipName());
        try {
            vqDao.insert(record);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("问卷表提交失败");
            throw new PlanVipNotFoundException(errCode11,errMsg11);
        }

        return 0;
    }

    @Override
    public Vipquestion selectByPrimaryKey(String openId) {
        Vipquestion vq;
        try {
            vq = vqDao.selectByPrimaryKey(openId);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("问卷表查询异常");
            throw new PlanVipNotFoundException(errCode11,errMsg11);
        }
        if (vq == null) {
            logger.error("该会员未提交问卷");
            throw new PlanVipNotFoundException(errCode18,errMsg18);
        }
        return vq;
    }

    @Override
    public List<Vipquestion> select(String query_date) {
        List<Vipquestion> vqlist;
        try {
            vqlist = vqDao.select(query_date);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("问卷表查询异常");
            throw new PlanVipNotFoundException(errCode11,errMsg11);
        }
        if (vqlist.size() == 0) {
            logger.error("该日期没有会员提交问卷");
            throw new PlanVipNotFoundException(errCode19,errMsg19);
        }
        return vqlist;
    }
}
