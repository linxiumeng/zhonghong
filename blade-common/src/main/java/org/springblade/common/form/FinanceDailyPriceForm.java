package org.springblade.common.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hanbin
 * faq的表单类
 */
@Data
public class FinanceDailyPriceForm {

    String code;

    Date startDate;

    Date endDate;

    List<Date> dateList;


}
