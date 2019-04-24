package org.springblade.common.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassworld {

    @NotBlank
    private String oldPassworld;

    @NotBlank
    private String newPassworld;
}
