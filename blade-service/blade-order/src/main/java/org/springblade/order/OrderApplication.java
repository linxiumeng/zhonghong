/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.order;

import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springblade.common.constant.ApplicationNameConstant.INFORMATION_MODULE;
import static org.springblade.common.constant.ApplicationNameConstant.ORDER_MODULE;

/**
 * 订单类信息 需求单 报价单 采购单
 *
 * @author Chill
 */
@SpringCloudApplication
@EnableScheduling
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@ComponentScan({"org.springblade"})
public class OrderApplication {

	public static void main(String[] args) {
		BladeApplication.run(ORDER_MODULE, OrderApplication.class, args);
	}

}