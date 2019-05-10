package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.common.entity.Demand;

import java.util.Date;

/**
 * 需求提交表单类
 * @author hanbin
 */
@Data
public class DemandForm extends Demand {


    private int page;

    private int size;

    private String key;



}
