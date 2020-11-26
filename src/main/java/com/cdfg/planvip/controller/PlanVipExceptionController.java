package com.cdfg.planvip.controller;


import cn.cdfg.exceptionHandle.PlanVipNotFoundException;
import com.cdfg.planvip.pojo.utill.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@ControllerAdvice
public class PlanVipExceptionController {

    @ExceptionHandler(value = PlanVipNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(produces="json/html; charset=UTF-8")
    @ResponseBody
    public Result<String> exception (PlanVipNotFoundException exception){
        System.out.println(exception.getMsg());
        return new Result<String>(exception.getRetCode(),exception.getMsg(),"");
    }
}
