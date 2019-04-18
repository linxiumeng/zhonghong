package org.springblade.common.respond;

import lombok.Data;
import org.springblade.common.entity.Quotation;

@Data
public class QuotationResp extends Quotation {

    String providerName;

    /**联系人*/
    private String providerContacts;
    /**联系电话*/
    private String providerContactNumber;
    /**联系地址*/
    private String providerContactAddress;

    /**
     * 郵箱
     */
    private String providerMail;

}
