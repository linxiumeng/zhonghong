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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.common.annotation.SysLog;
import org.springblade.bgadmin.common.utils.Constant;
import org.springblade.bgadmin.modules.sys.entity.SysMenuEntity;
import org.springblade.bgadmin.modules.sys.form.SysMenuForm;
import org.springblade.bgadmin.modules.sys.service.SysMenuService;
import org.springblade.bgadmin.modules.sys.service.SysRoleMenuService;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "系统菜单", description = " * @author jinzeze")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 导航菜单
	 */
	@PostMapping("/nav")
	@ApiOperation(value = "导航菜单", notes = "")
	public R nav(){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@PostMapping("/list")
	//@RequiresPermissions("sys:menu:list")
	@ApiOperation(value = "列表", notes = "")
	public R list(@RequestBody SysMenuForm sysMenuForm){
		List<SysMenuEntity> menuList = sysMenuService.list(new QueryWrapper<>());
		for(SysMenuEntity sysMenuEntity : menuList){
			SysMenuEntity parentMenuEntity = sysMenuService.getById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		Integer roleId = sysMenuForm.getRoleId();
		List<Long> menuRoleList = null;
		if(roleId != null){
			menuRoleList = sysRoleMenuService.queryMenuIdList(roleId.longValue());
		}

		return R.ok().put("result",menuList).put("roleMenuIdList",menuRoleList);
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@PostMapping("/select")
	@ApiOperation(value = "列表", notes = "")
	//@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@PostMapping("/info/{menuId}")
	//@RequiresPermissions("sys:menu:info")
	@ApiOperation(value = "菜单信息", notes = "")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.getById(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@PostMapping("/save")
	@ApiOperation(value = "保存菜单", notes = "")
	//@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
	@ApiOperation(value = "修改菜单", notes = "")
	//@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@PostMapping("/delete")
	@ApiOperation(value = "删除菜单", notes = "")
	//@RequiresPermissions("sys:menu:delete")
	public R delete(long menuId){
		if(menuId <= 31){
			return R.error("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
