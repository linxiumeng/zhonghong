package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.entity.Quotation;

@Data
public class QuotationForm extends Quotation {

    private String code;

    private int page;

    private int size;

}
