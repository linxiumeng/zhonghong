package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;

/**
 * @author hanbin
 * faq的表单类
 */
@Data
public class FaqForm extends BaseForm{

    private Long id;

    private Integer isOpen;

    private Integer order;

    private String title;

    private String content;

}
