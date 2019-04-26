package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.AccountEntity;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;
import org.springblade.bgadmin.modules.sys.enums.UserCreditStatusEnum;
import org.springblade.bgadmin.modules.sys.enums.UserStatusEnum;
import org.springblade.bgadmin.modules.sys.form.UserForm;
import org.springblade.bgadmin.modules.sys.service.AccountService;
import org.springblade.bgadmin.modules.sys.service.FuserService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@RestController
@RequestMapping("sys/fuser")
public class FuserController {
    @Autowired
    private FuserService fuserService;

    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:fuser:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fuserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("sys:fuser:info")
    public R info(@PathVariable("userId") Integer userId) {
            FuserEntity fuser = fuserService.getById(userId);

        return R.ok().put("fuser", fuser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:fuser:save")
    public R save(@RequestBody FuserEntity fuser) {
            fuserService.save(fuser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:fuser:update")
    public R update(@RequestBody FuserEntity fuser) {
        //ValidatorUtils.validateEntity(fuser);
            fuserService.updateById(fuser);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:fuser:delete")
    public R delete(@RequestBody Integer[] userIds) {
            fuserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }



    /**
     * 冻结和解冻用户，针对于采购商 todo 需要确认冻结状态所用的字段
     * @param userForm
     * @return
     */
    @PostMapping("freeze")
    //@RequiresPermissions("sys:shanghuliebiao:freeze")
    public R freeze(@RequestBody UserForm userForm) {

        if(userForm.getLoginStatus() == null){
            return R.error("参数错误");
        }

        FuserEntity userEntity = new FuserEntity();
        userEntity.setUserId(userForm.getUserId());
        userEntity.setLoginStatus(userForm.getLoginStatus());
        boolean flag = fuserService.updateById(userEntity);

        if (!flag) {
            return R.error("操作失败");
        }

        return R.ok("操作成功");
    }


    /**
     * 用户列表
     * @param userForm
     * @return
     */
    @PostMapping("user_list")
    //@RequiresPermissions("sys:shanghuliebiao:list")
    public R getUserAccount(@RequestBody UserForm userForm){
        IPage page = new Page(userForm.getPage(),userForm.getSize());
        QueryWrapper<FuserEntity> entityWrapper = new QueryWrapper();

        entityWrapper.eq("1",1);
        if(userForm.getStartDate() != null){
            entityWrapper.gt("create_date",userForm.getStartDate());
        }

        if(userForm.getEndDate() != null ){
            entityWrapper.lt("create_date",userForm.getEndDate());
        }

        if(userForm.getCreditStatus() != null){
            entityWrapper.eq("credit_status",userForm.getCreditStatus().getCode());
        }

        if(userForm.getStatus() != null){
            entityWrapper.eq("status",userForm.getStatus().getCode());
        }

        if(userForm.getLoginStatus() != null){
            entityWrapper.eq("login_status",userForm.getLoginStatus().getCode());
        }

        if (StringUtils.isNotBlank(userForm.getKeywords())) {
            //entityWrapper.andNew().eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords());
            entityWrapper.and(i->i.eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords()));
        }

     //   entityWrapper.orderBy("create_date",false);
        return R.ok().put("result",fuserService.listUserAccountByWrapper(page,entityWrapper));
    }


    /**
     * 用户列表
     * @param userForm
     * @return
     */
    @PostMapping("wait_review_list")
    //@RequiresPermissions("sys:shanghushenhe:list")
    public R getWaitReviewUserAccount(@RequestBody UserForm userForm){
        Page page = new Page(userForm.getPage(),userForm.getSize());
        QueryWrapper<FuserEntity> entityWrapper = new QueryWrapper();

        entityWrapper.eq("1",1);
        if(userForm.getStartDate() != null){
            entityWrapper.gt("attest.creat_time",userForm.getStartDate());
        }

        if(userForm.getEndDate() != null ){
            entityWrapper.lt("attest.creat_time",userForm.getEndDate());
        }

        if(userForm.getWaitPageConditionStatus() == null || (userForm.getWaitPageConditionStatus() != 1 && userForm.getWaitPageConditionStatus() != 2)){
            entityWrapper.in("status",new Object[]{1,2});
        } else {
            entityWrapper.eq("status",userForm.getWaitPageConditionStatus());
        }

        if (StringUtils.isNotBlank(userForm.getKeywords())) {
            //entityWrapper.andNew().eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords());
            entityWrapper.and(i->i.eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords()));
        }

        //   entityWrapper.orderBy("create_date",false);
        return R.ok().put("result",fuserService.listWaitReviewUserAccount(page,entityWrapper));
    }


    /**
     * 用户列表
     * @param userForm
     * @return
     */
    @PostMapping("wait_credit_list")
    //@RequiresPermissions("sys:shanghushouxin:list")
    public R getWaitCreditUserAccount(@RequestBody UserForm userForm){
        Page page = new Page(userForm.getPage(),userForm.getSize());
        QueryWrapper<FuserEntity> entityWrapper = new QueryWrapper();

        entityWrapper.eq("1",1);
        if(userForm.getStartDate() != null){
            entityWrapper.gt("credit.create_date",userForm.getStartDate());
        }

        if(userForm.getEndDate() != null ){
            entityWrapper.lt("credit.create_date",userForm.getEndDate());
        }

        entityWrapper.eq("status", UserCreditStatusEnum.ACCEPT.getCode());

        if(userForm.getCreditStatus() != null){
            entityWrapper.eq("credit_status",userForm.getCreditStatus().getCode());
        }else {
            entityWrapper.in("credit_status",new Object[]{1,2,3});
        }

        if (StringUtils.isNotBlank(userForm.getKeywords())) {
            //entityWrapper.andNew().eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords());
            entityWrapper.and(i->i.eq("mobile",userForm.getKeywords()).or().eq("company_name",userForm.getKeywords()));
        }

        //   entityWrapper.orderBy("create_date",false);
        return R.ok().put("result",fuserService.listWaitCreditUserAccount(page,entityWrapper));
    }



    @PostMapping("detail")
    //@RequiresPermissions(value = {"sys:shanghuliebiao:detail","sys:shanghushouxin:detail"},logical = Logical.OR)
    public R getUserAccountById(@Param("id")Integer id){
        return R.ok().put("result",fuserService.getUserAccountByUserId(id));
    }


    /**
     * 审核
     * @param userForm
     * @return
     */
    @PostMapping("review")
    //@RequiresPermissions("sys:shanghushenhe:verify")
    public R riewUserById(@RequestBody UserForm userForm){

        Integer userId = userForm.getUserId();
        UserStatusEnum userStatusEnum = userForm.getStatus();

        if(userId == null || userStatusEnum == null){
            return R.error("参数缺失");
        }

        FuserEntity fuserEntity = new FuserEntity();
        fuserEntity.setUserId(userId);
        fuserEntity.setStatus(userStatusEnum);

        boolean flag = fuserService.updateById(fuserEntity);
        if(flag){
            return R.ok();
        }
        return R.error("操作失败请联系管理员");
    }


    /**
     * 审核
     * @param userForm
     * @return
     */
    @PostMapping("credit")
    //@RequiresPermissions("sys:shanghushouxin:credit")
    public R creditUserById(@RequestBody UserForm userForm){

        Integer userId = userForm.getUserId();
        UserCreditStatusEnum userCreditStatusEnum = userForm.getCreditStatus();

        if(userId == null || userCreditStatusEnum == null){
            return R.error("参数缺失");
        }

        if(userForm.getCreditStatus() == UserCreditStatusEnum.ACCEPT && (StringUtils.isBlank(userForm.getCreditHigh()) || StringUtils.isBlank(userForm.getCreditLevel()) || StringUtils.isBlank(userForm.getCreditUnit())|| StringUtils.isBlank(userForm.getCreditLimit()))){
            return R.error("参数缺失");
        }

        FuserEntity fuserEntity = new FuserEntity();
        fuserEntity.setUserId(userId);
        fuserEntity.setCreditStatus(userCreditStatusEnum);

        AccountEntity accountEntity = accountService.getOne(new QueryWrapper<AccountEntity>().eq("user_id",userId));

        if(accountEntity == null){
            return R.error("账户资料缺失请联系管理员");
        }

        accountEntity.setCreditHigh(userForm.getCreditHigh());
        accountEntity.setCreditLevel(userForm.getCreditLevel());
        accountEntity.setCreditUnit(userForm.getCreditUnit());
        accountEntity.setCreditLimit(userForm.getCreditLimit());

        boolean flag = fuserService.updateById(fuserEntity);
        flag = accountService.update(accountEntity,new QueryWrapper<AccountEntity>().eq("user_id",userId));
        if(flag){
            return R.ok();
        }
        return R.error("操作失败请联系管理员");
    }






}
