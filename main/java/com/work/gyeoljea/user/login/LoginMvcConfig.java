package com.work.gyeoljea.user.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 컨트롤러를 통해 뷰를 전달하는 대신 
// 뷰와 요청을 매핑해주는 역할
@Configuration
public class LoginMvcConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main/main");
		registry.addViewController("/login").setViewName("login/login");
		registry.addViewController("/admin").setViewName("main/admin");
		registry.addViewController("/signup").setViewName("signup/signup");		
	}


}
