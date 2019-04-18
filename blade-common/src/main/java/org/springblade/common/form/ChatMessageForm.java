package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 聊天表单
 */
@Data
public class ChatMessageForm extends PageForm{

    private Integer from;

    private Integer to;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date fromDate;

}
