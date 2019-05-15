package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 站内信实体类
 */
@Data
@TableName("tb_message")
@ApiModel("站内信实体类")
public class Message {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id",name="id",example = "1")
    private Long id;

    /**
     * 发送人
     */
    @ApiModelProperty(value = "发送人",name="id",example = "帅泽泽")
    private String from;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人",name="id",example = "美娟娟")
    private String to;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",name="id",example = "uc震惊部")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容",name="id",example = "先这样，再这样")
    private String content;

    /**
     * 状态 0 - 未读 ， 1 - 已读
     */
    @ApiModelProperty(value = "状态 0 - 未读 ， 1 - 已读",name="id",example = "2019-12-01 12:23")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="id",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="id",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

}
