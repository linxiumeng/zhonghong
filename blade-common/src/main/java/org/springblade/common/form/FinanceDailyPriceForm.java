package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.enums.PriceDateEnum;

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

    PriceDateEnum type;

    /**
     * 多少天之前的
     */
    Integer preDays;


}
