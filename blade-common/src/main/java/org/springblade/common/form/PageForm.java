package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hanbin
 * 分页表单
 */
@Data
@ApiModel(description = "分页表单")
public class PageForm {

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

    /**
     * 关键字
     */
    @ApiModelProperty(example = "林秀栋董事长", value = "关键字")
    private String key;
}
