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

package org.springblade.bgadmin.modules.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Shiro权限标签
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月3日 下午11:32:47
 */
@Component
public class ShiroTag {

	@Resource
	UserRealm userRealm;

	/**
	 * 是否拥有该权限
	 * @param permission  权限标识
	 * @return   true：是     false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

	/**
	 * 获取认证消息
	 * @return
	 */
	public AuthorizationInfo getAllPermission(){
		Subject subject = SecurityUtils.getSubject();
		if(subject != null) {
			return userRealm.doGetAuthorizationInfo(subject.getPrincipals());
		}
		return null;
	}

}
