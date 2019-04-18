package org.springblade.common.respond;

import lombok.Data;
import org.springblade.common.entity.Demand;
import org.springblade.common.entity.Quotation;

import java.io.Serializable;
import java.util.List;

@Data
public class DemandResp extends Demand implements Serializable{



    private List<Quotation> quotationList;




}
