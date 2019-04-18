package org.springblade.common.respond;

import lombok.Data;

@Data
public class ProviderPurchaseOrderStatisticsResp {

    /**
     *确认等待
     */
    private int waitConfirmation;

    /**
     * 等待确认价格
     */
    private int makePriceWaiting;

    /**
     * 定价被打回
     */
    private int makePriceRefuse;

    /**
     * z转款确认
     */
    private int transferMoneyConfirmation;


}
