package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.MessageDao;
import org.springblade.bgadmin.modules.sys.entity.MessageEntity;
import org.springblade.bgadmin.modules.sys.service.MessageService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageEntity> page = this.page(
                new Query<MessageEntity>(params).getPage(),
                new QueryWrapper<MessageEntity>()
        );

        return new PageUtils(page);
    }

}
