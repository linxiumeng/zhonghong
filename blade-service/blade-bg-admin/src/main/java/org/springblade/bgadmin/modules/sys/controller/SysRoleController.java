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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.SysRoleEntity;
import org.springblade.bgadmin.modules.sys.form.BaseForm;
import org.springblade.bgadmin.modules.sys.service.SysRoleDeptService;
import org.springblade.bgadmin.modules.sys.service.SysRoleMenuService;
import org.springblade.bgadmin.modules.sys.service.SysRoleService;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理", description = " * @author jinzeze")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;
	
	/**
	 * 角色列表
	 */
	@PostMapping("/list")
	//@RequiresPermissions("sys:role:list")
	@ApiOperation(value = "角色列表", notes = "")
	public R list(@RequestBody BaseForm baseForm){
	//	PageUtils page = sysRoleService.queryPage(params);

		IPage page = new Page(baseForm.getPage(),baseForm.getSize());
		return R.ok().put("page", sysRoleService.page(page));
	}
	
	/**
	 * 角色列表
	 */
	@PostMapping("/select")
	@ApiOperation(value = "角色列表", notes = "")
	//@RequiresPermissions("sys:role:select")
	public R select(){
		List<SysRoleEntity> list = sysRoleService.list(null);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@PostMapping("/info/{roleId}")
	@ApiOperation(value = "角色信息", notes = "")
	//@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		//查询角色对应的部门
		List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
		role.setDeptIdList(deptIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
//	@SysLog("保存角色")
	@PostMapping("/save")
	@ApiOperation(value = "保存角色", notes = "")
	//@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		//ValidatorUtils.validateEntity(role);
		
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
//	@SysLog("修改角色")
	@PostMapping("/update")
	@ApiOperation(value = "修改角色", notes = "")
	//@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		//ValidatorUtils.validateEntity(role);
		
	//	sysRoleService.updateById(role);
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
//	@SysLog("删除角色")
	@PostMapping("/delete")
	@ApiOperation(value = "删除角色", notes = "")
	//@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
}
