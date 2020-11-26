package com.cdfg.planvip.controller;

import com.cdfg.planvip.pojo.dto.OpenID;
import com.cdfg.planvip.pojo.dto.Viptime;
import com.cdfg.planvip.pojo.utill.Result;
import com.cdfg.planvip.service.VipTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.cdfg.planvip.pojo.utill.Constant.sucCode;
import static com.cdfg.planvip.pojo.utill.Constant.sucMsg;

@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class VipTimeController {

    @Autowired
    VipTimeService viptimeservice = null;


    @PostMapping("insertVipTime")
    @ResponseBody
    public Result<String> insertVipTime(@RequestBody Viptime viptime) {
        viptimeservice.insert(viptime);
        return null;
    }

    @PostMapping("qryVipTime")
    @ResponseBody
    public Result<Viptime> qryVipTime(@RequestBody OpenID open_id) {
        String openId = open_id.getOpen_id();
        Viptime vt = viptimeservice.selectByPrimaryKey(openId);
        return new Result<Viptime>(sucCode,sucMsg,vt);
    }
}
