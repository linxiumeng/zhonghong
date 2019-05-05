package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.enums.GoodsStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 */
@Data
public class GoodsStatusForm {
    //  @NotNull
    private Long id;
    //  @NotNull
    private GoodsStatusEnum status;
    //   @NotBlank
    private int code;
}
