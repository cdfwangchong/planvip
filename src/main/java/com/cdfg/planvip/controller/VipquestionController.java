package com.cdfg.planvip.controller;

import cn.cdfg.exceptionHandle.PlanVipNotFoundException;
import com.cdfg.planvip.pojo.dto.OpenID;
import com.cdfg.planvip.pojo.dto.QueryDate;
import com.cdfg.planvip.pojo.dto.Vipquestion;
import com.cdfg.planvip.pojo.utill.Result;
import com.cdfg.planvip.service.VipquestionService;
import com.cdfg.planvip.service.impl.VipquestionServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cdfg.planvip.pojo.utill.Constant.*;

@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class VipquestionController {
    @Autowired
    VipquestionService vqservice = null;

    Logger logger = Logger.getLogger(VipquestionController.class);

    @PostMapping("insertVipQuestion")
    @ResponseBody
    public Result<String> insertVipQuestion(@RequestBody Vipquestion vipst) {

        int ret = vqservice.insert(vipst);
        if (ret == 0) {
            return new Result<String>(sucCode,sucMsg,"1002");
        }else {
            return new Result<String>(sucCode,sucMsg,"1001");
        }
    }

    @PostMapping("qryUserQuest")
    @ResponseBody
    public Result<Vipquestion> qryUserQuest(@RequestBody OpenID oi) {

        Vipquestion vq = vqservice.selectByPrimaryKey(oi.getOpen_id());

        if (vq == null) {
            logger.error("该会员未提交问卷");
            throw new PlanVipNotFoundException(errCode18,errMsg18);
        }else {
            return new Result<Vipquestion>(sucCode,sucMsg,vq);
        }
    }


    @PostMapping("qryDateQuest")
    @ResponseBody
    public Result<List<Vipquestion>> qryDateQuest(@RequestBody QueryDate qd) {

        List<Vipquestion> vqlist = vqservice.select(qd.getQuery_date());

        if (vqlist.size() == 0) {
            logger.error("该会员未提交问卷");
            throw new PlanVipNotFoundException(errCode18,errMsg18);
        }else {
            return new Result<List<Vipquestion>>(sucCode,sucMsg,vqlist);
        }
    }
}
