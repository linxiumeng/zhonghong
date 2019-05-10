package org.springblade.common.form;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.UserEntity;

import java.util.Date;

/**
 * @author hanbin
 * 拓展UserEntityz增加分页功能
 */
@Data
@TableName("tb_fuser")
@ApiModel("注册表单")
public class UserForm extends UserEntity {

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间",name="startDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间",name="endDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 用于查询的关键字
     */
    @ApiModelProperty(example = "泽泽", name="keywords",value = "用于查询的关键字")
    private String keywords;

    /**
     * 状态：用于分别身份类型
     */
    @ApiModelProperty(value = "状态：用于分别身份类型",name="status",example = "1")
    private Integer status;

    /**
     * 授信状态
     */
    @ApiModelProperty(value = "授信状态",name="creditStatus",example = "0")
    private Integer creditStatus;

    /**
     * 当前页
     */
    @ApiModelProperty(example = "1",value = "当前页")
    private int page;

    /**
     * 每页条数
     */
    @ApiModelProperty(example = "10",value = "每页条数")
    private int size;



}
