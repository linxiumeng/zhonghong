package org.springblade.forewarduser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Attest;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.UserStatusEnum;
import org.springblade.forewarduser.mapper.AttestDao;
import org.springblade.forewarduser.service.AttestService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * (Attest)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:00:29
 */
@Service("attestService")
public class AttestServiceImpl extends ServiceImpl<AttestDao, Attest> implements AttestService {
    @Resource
    private AttestDao attestDao;

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createAttest(Attest attest) {

        UserEntity param1 = this.userService.getById(attest.getUserId());
        param1.setStatus(UserStatusEnum.VERIFYING.ordinal());
        this.userService.updateById(param1);
        attest.setUserId(attest.getUserId());
        return this.saveOrUpdate(attest);

    }
}