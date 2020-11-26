package com.cdfg.planvip.pojo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * vipquestion
 * @author 
 */
@Data
public class Vipquestion implements Serializable {
    /**
     * 序号（同vipuser表）
     */
    private Integer seqno;

    /**
     * 证件号
     */
    private String cardId;

    /**
     * 电话号码
     */
    private String telNum;

    /**
     * 了解哪些品牌
     */
    private String qustKnowdbrand;

    /**
     * 喜欢哪个品牌
     */
    private String qustLikebrand;

    /**
     * 希望收到的礼物
     */
    private String qustGift;

    /**
     * 推送促销活动短信
     */
    private String quesSendmsg;

    /**
     * 贵宾服务
     */
    private String qustVipservice;

    /**
     * 创建时间
     */
    private Date creatTime;


    private String open_id;

    private String vipName;

    private static final long serialVersionUID = 1L;
}