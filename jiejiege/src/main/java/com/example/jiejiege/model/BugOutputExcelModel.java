package com.example.jiejiege.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ColumnWidth(22)
@EqualsAndHashCode
public class BugOutputExcelModel {
    @ExcelProperty("缺陷id")
    private Integer id;

    @ExcelProperty(value = "缺陷名称")
    private String bugName;

    @ExcelProperty(value = "缺陷类型")
    private String bugKind;

    @ExcelProperty(value = "确认")
    private String confirm;

    @ExcelProperty(value = "优先级")
    private Integer priority;

    @ExcelProperty(value = "缺陷状态")
    private String status;

    @ExcelProperty(value = "创建者")
    private String creatorName;

    @ExcelProperty(value = "所属项目")
    private String productName;

    @ExcelProperty(value = "所属模块")
    private String functionName;

    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    @ExcelProperty(value = "被指派者")
    private String designeeName;

    @ExcelProperty(value = "解决时间")
    private Date solveTime;

    @ExcelProperty(value = "缺陷描述")
    private String bugRemark;

}