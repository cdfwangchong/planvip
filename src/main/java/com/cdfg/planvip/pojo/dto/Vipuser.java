package com.cdfg.planvip.pojo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * vipuser
 * @author 
 */
@Data
public class Vipuser implements Serializable {

    /**
     * 证件号
     */
    private String cardId;

    /**
     * 姓名
     */
    private String vipName;

    /**
     * 电话号码
     */
    private String telNum;

    /**
     * 会员类型
     */
    private String vipType;

    /**
     * 有效期
     */
    private Date vipDate;

    /**
     * 创建时间
     */
    private Date creatTime;


    private String openId;

    private static final long serialVersionUID = 1L;
}