package org.springblade.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassword {
    @ApiModelProperty(value = "oldPassword",example = "123456")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @ApiModelProperty(value = "oldPassword",example = "1234567")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
