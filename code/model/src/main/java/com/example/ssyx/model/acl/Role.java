package com.example.ssyx.model.acl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.ssyx.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data     //lombok注解
@ApiModel(description = "角色") //swagger注解提示
@TableName("role")   //实体类对应角色表role
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")   //swagger注解提示
    @TableField("role_name")    //属性对应的表的字段 role_name
    private String roleName;

    @ApiModelProperty(value = "备注")
    @TableField("remark")       //属性对应的表的字段 remark
    private String remark;

}