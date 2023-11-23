package com.example.jiejiege.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_bug")
public class Bug implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //缺陷名称
    private String bugName;
    //缺陷类型
    private String bugKind;
    //优先级，1、2、3、4
    private Integer priority;
    //0未解决1已解决2已关闭3激活
    private Integer status;
    //0待确认1已确认2已拒绝
    private Integer confirm;
    //创建者id
    private Integer creatorId;
    //功能模块id
    private Integer functionId;
    //更新时间
    private Date updateTime;
    //被指派者id
    private Integer designeeId;
    //解决时间
    private Date solveTime;
    //缺陷描述
    private String bugRemark;
}