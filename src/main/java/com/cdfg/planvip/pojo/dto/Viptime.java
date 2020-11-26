package com.cdfg.planvip.pojo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * viptime
 * @author 
 */
@Data
public class Viptime implements Serializable {
    /**
     * 序号
     */
    private Long seqno;

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
     * 预约日期
     */
    private Date vipTime;

    /**
     * 预约时段
     */
    private String vipYysd;

    /**
     * 是否取消
     */
    private String iscancel;

    /**
     * 是否更新
     */
    private String isupdate;

    /**
     * 原预约日期
     */
    private Date preTime;

    /**
     * 原预约时段
     */
    private String preYysd;

    /**
     * openId
     */
    private String open_id;

    private static final long serialVersionUID = 1L;
}