package org.springblade.common.form;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * faq的表单类
 */
@Data
public class FinanceDailyPriceForm {

    String code;

    Date startDate;

    Date endDate;


}
