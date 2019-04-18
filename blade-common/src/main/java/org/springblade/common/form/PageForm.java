package org.springblade.common.form;

import lombok.Data;

@Data
public class PageForm {
    private int page;
    private int size;
    private String key;
}
