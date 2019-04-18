package org.springblade.forewarduser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Account;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.PayForm;

/**
 * 商品表(TbGoods)表服务接口
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
public interface AccountService extends IService<Account> {
    /**
     * 支付
     * @param param
     * @param user
     * @return
     */
    boolean pay(PurchaseOrders param, UserEntity user);

    /**
     * 金融支付？？？
     * @param po
     * @param user
     * @param param
     * @return
     */
    boolean financingPay(PurchaseOrders po, UserEntity user, PayForm param);
}