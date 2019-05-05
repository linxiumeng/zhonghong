package org.springblade.bgadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableScheduling
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@ComponentScan({"org.springblade"})
@MapperScan(basePackages = {"org.springblade.bgadmin.modules.*.dao"})
public class BgAdminApplication{

	public static void main(String[] args) {
		//SpringApplication.run(BgAdminApplication.class, args);
		BladeApplication.run("blade-bg-admin",BgAdminApplication.class,args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BgAdminApplication.class);
	}*/
}
