package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.FaqEntity;

import java.util.Date;

/**
 * @author hanbin
 * 常见问题义务为操作层
 */
public interface FaqService extends IService<FaqEntity> {

    /**
     * faq列表
     *
     * @param page
     * @param startDate
     * @param endDate
     * @param keywords
     * @return
     */
    IPage<FaqEntity> listFaq(IPage page, Date startDate, Date endDate, String keywords,Integer status);

}
