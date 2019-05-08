package org.springblade.common.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassword {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
