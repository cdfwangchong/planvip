package com.cdfg.planvip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdfg.exceptionHandle.PlanVipNotFoundException;
import com.cdfg.planvip.pojo.dto.RegUser;
import com.cdfg.planvip.pojo.dto.Vipquestion;
import com.cdfg.planvip.pojo.dto.Vipuser;
import com.cdfg.planvip.pojo.utill.*;
import com.cdfg.planvip.service.VipquestionService;
import com.cdfg.planvip.service.VipuserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.cdfg.exceptionHandle.ExceptionPrintMessage;

import com.alibaba.fastjson.JSONObject;
import static com.cdfg.planvip.pojo.utill.Constant.*;

/*
 * project name :贵宾计划
 * for:用户注册
 * author：wangc
 * time：2020-11-23
 * */

@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class UserController {

    @Autowired
    private VipuserService vuservice = null;

    @Autowired
    private VipquestionService vqservice = null;

    Logger logger = Logger.getLogger(UserController.class);

    /**
     * 注册接口
     * @param reguser
     * @return
     * @throws NullPointerException
     */
    @PostMapping("/getregister")
    @ResponseBody
    public Result<String> cdfgdeposit(@RequestBody RegUser reguser) throws NullPointerException {

        String id_type = reguser.getId_type();//证件类型
        String card_id = reguser.getCard_id();//证件号
        String tel_num = reguser.getTel_num();//手机号
        String open_id = reguser.getOpen_id();//密码

        logger.info("取到注册用户信息"+id_type+card_id+tel_num+open_id);

        Map<String,String> param=new HashMap<String,String>();
        param.put("id_type", id_type);
        param.put("card_id", card_id);
        param.put("tel_num", tel_num);
        param.put("open_id", open_id);

        Vipuser vipuser;
        try {
            vipuser = vuservice.selectByPrimary(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查询顾客信息失败");
            throw new PlanVipNotFoundException(errCode,errMsg);
        }
        if (vipuser == null) {
            logger.error("会员名单查询为空，不能注册");
            throw new PlanVipNotFoundException(errCode16,errMsg16);
        }

        int ret = 0;
        try {
            ret = vuservice.updateByPrimaryKey(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("更新失败");
            throw new PlanVipNotFoundException(errCode,errMsg);
        }
        if (ret != 1) {
            logger.error("注册失败");
            throw new PlanVipNotFoundException(errCode17,errMsg17);
        }else {
            return new Result<String>(sucCode,sucMsg,"");
        }
    }

    /**
     * 登录接口
     * @param login
     * @return
     */
    @PostMapping("/getlogin")
    @ResponseBody
    public Result<Map> login(@RequestBody Login login) {

        String openId = login.getOpen_id();//密码
        Map<String,String> retparam=new HashMap<String,String>();

        logger.info("取到登录用户信息"+openId);

        String user_name = null;
        String card_id = null;
        String tel_num = null;
        Vipuser vu;
        try {
             vu= vuservice.selectByPrimaryKey(openId);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("取到登录用户信息存储过程的返回值异常");
            throw new PlanVipNotFoundException(errCode9,errMsg9);
        }

        if (vu == null) {
            logger.error("未注册，无记录");
            throw new PlanVipNotFoundException(errCode7,errMsg7);
        }else {
            retparam.put("card_id",vu.getCardId());
            retparam.put("tel_num",vu.getTelNum());
            retparam.put("user_name",vu.getVipName());

        }
        Vipquestion vq;
        try {
            vq = vqservice.selectByPrimaryKey(vu.getCardId());
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("取到用户的问卷失败");
            throw new PlanVipNotFoundException(errCode9,errMsg9);
        }

        if (vq == null) {
            logger.error("无问卷记录");
            retparam.put("is_answer","N");
        }else {
            retparam.put("is_answer","Y");
        }
        logger.info("获取登录信息用户名："+user_name+"身份证："+card_id+"电话："+tel_num);
        return new Result<Map>(sucCode,sucMsg,retparam);
    }

    /**
     * 获取Openid接口
     * @param code
     * @return
     */
    @PostMapping("/getopenid")
    @ResponseBody
    public Result<Map> wechatopenid(@RequestBody WechatCode code) {

        Map<String,String> retparam=new HashMap<String,String>();
        String vercode = code.getVer_code();

        logger.info("取到code"+vercode);

        JSONObject jsonObject;
        jsonObject = AuthUtil.doGetJson(vercode);

        //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
        if (jsonObject.containsKey("access_token")) {
            String token =  jsonObject.getString("access_token");
            String openId = jsonObject.getString("openid");

            logger.info("token:"+token+";"+"openid:"+openId);

            //获取到客人信息
            Map<String,String> param=new HashMap<String,String>();
            param.put("open_id", openId);

            logger.info("获取到客人openid："+openId);

            Vipuser vu;
            try {
                vu= vuservice.selectByPrimaryKey(openId);
            } catch (Exception e) {
                logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
                logger.error("取到登录用户openid返回值异常");
                throw new PlanVipNotFoundException(errCode9,errMsg9);
            }
            if (vu == null) {
                logger.error("未注册，无记录");
                throw new PlanVipNotFoundException(errCode7,errMsg7);
            }else {
                retparam.put("card_id",vu.getCardId());
                retparam.put("tel_num",vu.getTelNum());
                retparam.put("user_name",vu.getVipName());
            }
        }else {
            String errcode =  jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");

            logger.info("errcode:"+errcode+";"+"errmsg:"+errmsg);
            logger.info("ret_result（返回结果）:"+"errcode:"+errcode+"errmsg:"+errmsg);
            throw new PlanVipNotFoundException(errCode8,errMsg8);
        }
        return new Result<Map>(sucCode,sucMsg,retparam);
    }

    /**
     *
     * @param vulist
     * @return
     */
    @PostMapping("/insertTotalUser")
    @ResponseBody
    public Result<String> insertTotalUser(@RequestBody List<Vipuser> vulist) {

        vuservice.insertSelective(vulist);

        return new Result<String>(sucCode,sucMsg,"");
    }
}
