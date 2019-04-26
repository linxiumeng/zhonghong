package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.modules.sys.dao.FaqDao;
import io.finepetro.modules.sys.entity.FaqEntity;
import io.finepetro.modules.sys.service.FaqService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hanbin
 * 常见问题业务操作层
 */
@Service
public class FaqServiceImpl extends ServiceImpl<FaqDao, FaqEntity> implements FaqService {
    @Override
    public Page<FaqEntity> listFaq(Page page, Date startDate, Date endDate, String keywords) {
        Wrapper wrapper = new EntityWrapper();
        if (endDate != null) {
            wrapper.lt("create_date", endDate);
        }
        if (startDate != null) {
            wrapper.gt("create_date", startDate);
        }
        if (StringUtils.isNotBlank(keywords)) {
            wrapper.like("title", keywords);
        }
        // 是否显示倒叙 + 序号倒叙 + 创建日期倒叙
        wrapper.orderBy("is_open",false).orderBy("`order`",false).orderBy("create_date",false);
        return this.selectPage(page, wrapper);
    }
}
