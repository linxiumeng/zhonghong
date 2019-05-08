package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hanbin
 * 聊天表单
 */
@ApiModel("聊天分页表单")
@Data
public class ChatMessageForm extends PageForm{

    /**
     * 来源
     */
    @NotNull
    @ApiModelProperty(value = "来源",example = "12")
    private Integer from;

    /**
     * 去处
     */
    @NotNull
    @ApiModelProperty(value = "去处",example = "21")
    private Integer to;

    /**
     * 日期
     */
    @NotNull
    @ApiModelProperty(value = "来源日期",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date fromDate;

}
