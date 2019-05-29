/**
 * Copyright 2018 首页 http://www.finepetro.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springblade.bgadmin.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springblade.bgadmin.modules.sys.entity.*;
import org.springblade.bgadmin.modules.sys.form.BaseForm;
import org.springblade.bgadmin.modules.sys.service.*;
import org.springblade.bgadmin.modules.sys.shiro.ShiroTag;
import org.springblade.bgadmin.modules.sys.shiro.ShiroUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "系统用户", description = " * @author jinzeze")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	FuserService userService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	AccountRepaymentStepService accountRepaymentStepService;

	@Autowired
	AccountRechargeService accountRechargeService;

	@Autowired
	AccountWithdrawService accountWithdrawService;

	@Autowired
	ShiroTag shiroTag;
	
	/**
	 * 所有用户列表
	 */
	@PostMapping("/list")
//	@RequiresPermissions("sys:user:list")
	@ApiOperation(value = "所有用户列表", notes = "")
	public R list(@RequestBody BaseForm baseForm){
	//	PageUtils page = sysUserService.queryPage(params);

	//	return R.ok().put("page", page);

		IPage page = new Page(baseForm.getPage(),baseForm.getSize());
		page = sysUserService.listSysUser(page,new QueryWrapper());

		return R.ok().put("page",page);

	//	return null;
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@PostMapping("/info")
	@ApiOperation(value = "获取登录的用户信息", notes = "")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	//@SysLog("修改密码")
	@PostMapping("/password")
	@ApiOperation(value = "修改登录用户密码", notes = "")
	public R password(String password, String newPassword){
	//	Assert.isBlank(newPassword, "新密码不为能空");

		//原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		//新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@PostMapping("/info/{userId}")
	@ApiOperation(value = "用户信息", notes = "")
	//@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
//	@SysLog("保存用户")
	@PostMapping("/save")
	@ApiOperation(value = "保存用户", notes = "")
	//@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
	//	ValidatorUtils.validateEntity(user, AddGroup.class);
		
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
//	@SysLog("修改用户")
	@PostMapping("/update")
	@ApiOperation(value = "修改用户", notes = "")
	//@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);

	//	sysUserService.updateById(user);

		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
//	@SysLog("删除用户")
	@PostMapping("/delete")
	@ApiOperation(value = "删除用户", notes = "")
	//@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.removeByIds(Arrays.asList(userIds));
		
		return R.ok();
	}


	@PostMapping("statistics")
	@ApiOperation(value = "统计", notes = "")
	public R listIndexInfo(){

		AuthorizationInfo authorizationInfo = shiroTag.getAllPermission();
		Collection<String> collection = authorizationInfo.getStringPermissions();

		Map<String,Integer> statisticsMap = new HashMap<>(8);
		if(collection.contains("sys:shanghushenhe:verify")) {
			//企业待认证个数
			// 过滤了身份状态不是0,1的用户an
			int companyWaitVerificationCount = userService.count(new QueryWrapper<FuserEntity>().notIn("status",2,3));
			statisticsMap.put("companyWaitVerificationCount", companyWaitVerificationCount);
		}

		if(collection.contains("sys:shanghushouxin:credit")) {
			//采购商待授信个数
			// 过滤了授信状态是0 ，并且是采购商的用户
			int purcharesWaitCreditCount = userService.count(new QueryWrapper<FuserEntity>().eq("status",3).notIn("credit_status", 3));
			statisticsMap.put("purchaesWaitCreditCount", purcharesWaitCreditCount);
		}

		if(collection.contains("sys:chanpinshenhe:verify")) {
			//商品待审核个数
			// 直接过滤状态是待审核的商品
		int goodsWaitVerificationCount = goodsService.count(new QueryWrapper<GoodsEntity>().eq("audit_status", 0));
			statisticsMap.put("goodsWaitVerificationCount", goodsWaitVerificationCount);
		}

		if(collection.contains("sys:huankuanliushui:list")) {
			//还款已逾期笔数
			int moneyWaitBackOverDateCount = accountRepaymentStepService.count(new QueryWrapper<AccountRepaymentStepEntity>().eq("status", 2));
			statisticsMap.put("moneyWaitBackOverDateCount", moneyWaitBackOverDateCount);
		}

		if(collection.contains("sys:chongzhishenpi:verify")) {
			//充值待审核笔数
			int rechargeWaitVerificationCount = accountRechargeService.count(new QueryWrapper<AccountRechargeEntity>().eq("status", 0));
			statisticsMap.put("rechargeWaitVerificationCount", rechargeWaitVerificationCount);
		}

		if(collection.contains("sys:tixianshenpi:verify")) {
			//提现待审批个数
			int withDrawVerificationCount = accountWithdrawService.count(new QueryWrapper<AccountWithdrawEntity>().eq("status", 0));
			statisticsMap.put("withDrawVerificationCount", withDrawVerificationCount);
		}

		return R.ok().put("statisticsEntity",statisticsMap);

	}

	//public static void main(String[] args) {
	//	System.out.println(ShiroUtils.sha256("hanbin", "4FHgJKzFsXrl8SBEGBhP"));
	//}


}
