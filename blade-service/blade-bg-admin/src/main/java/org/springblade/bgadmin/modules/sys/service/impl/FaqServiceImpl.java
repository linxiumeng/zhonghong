package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.mapper.FaqDao;
import org.springblade.bgadmin.modules.sys.entity.FaqEntity;
import org.springblade.bgadmin.modules.sys.service.FaqService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hanbin
 * 常见问题业务操作层
 */
@Service
public class FaqServiceImpl extends ServiceImpl<FaqDao, FaqEntity> implements FaqService {
    @Override
    public IPage<FaqEntity> listFaq(IPage page, Date startDate, Date endDate, String keywords,Integer status) {
        QueryWrapper wrapper = new QueryWrapper();
        if (endDate != null) {
            wrapper.lt("create_date", endDate);
        }
        if (startDate != null) {
            wrapper.gt("create_date", startDate);
        }
        if (StringUtils.isNotBlank(keywords)) {
            wrapper.like("title", keywords);
        }

        if(status != null){
            wrapper.eq("is_open",status);
        }

        // 是否显示倒叙 + 序号倒叙 + 创建日期倒叙
        wrapper.orderBy(true,false,"is_open").orderBy(true,false,"`order`").orderBy(true,false,"create_date");
        return this.page(page, wrapper);
    }
}
