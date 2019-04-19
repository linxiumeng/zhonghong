package org.springblade.forewarduser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Credit;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.exception.RRException;
import org.springblade.forewarduser.mapper.CreditDao;
import org.springblade.forewarduser.service.CreditService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 授信表(Credit)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
@Service("creditService")
public class CreditServiceImpl extends ServiceImpl<CreditDao, Credit> implements CreditService {
    @Resource
    private CreditDao creditDao;

    @Resource
    UserService userService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createCredit(Credit credit, UserEntity user) {

        credit.setUserId(user.getUserId());
        if(user.getStatus() != 3){
            throw new RRException("用户未通过认证");
        }

        user.setCreditStatus(1);
        userService.updateById(user);

        return this.save(credit);

    }
}