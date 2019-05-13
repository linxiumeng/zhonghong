package org.springblade.common.form;

import lombok.Data;

/**
 * @author hanbin
 * faq的表单类
 */
@Data
public class FaqForm{

    private Long id;

    private Integer isOpen;

    private Integer order;

    private String title;

    private String content;

    private int page;

    private int size;

    private String keywords;

}
