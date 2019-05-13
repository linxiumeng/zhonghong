package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Faq;
import org.springblade.information.mapper.FaqDao;
import org.springblade.information.service.FaqService;
import org.springframework.stereotype.Service;

/**
 * @author hanbin
 * 常见问题业务操作层
 */
@Service
public class FaqServiceImpl extends ServiceImpl<FaqDao, Faq> implements FaqService {

}
