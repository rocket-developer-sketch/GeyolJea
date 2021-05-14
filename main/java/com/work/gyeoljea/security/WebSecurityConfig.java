package com.work.gyeoljea.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.work.gyeoljea.user.login.UserService;

import lombok.RequiredArgsConstructor;


/*
 * WebSecurityConfigurerAdapter:
 * Spring Security 설정 파일 역할 
 */


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserService userService;

	/*
	 * configure(WebSecurty web)
	 *	WebSecurity는filterChainProxy를 생성하는 필터입니다.
 */
	// 인증 무시할 경로 설정
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "favicon*");	
		
		/* static 자원 모두 제외 하는 법
		 	web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); 
	    */
	}
	
	/*
	 * configure(HttpSecurity http)
	 *	HttpSecurity를 통해 HTTP요청에 대한 웹 기반의 보안을 구성할 수 있습니다.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* http 관련 인증
			 anyMatchers 통해 경로 설정 과 권한 설정 가능
			 permitAll() : 누구나 접근가능
			 hasRole() : 특정 권한이 있는 사람만 접근 가능
			 authenticated() : 권한이 있으면 무조건 접근 가능
			 anyRequest는 anyMatcher에서 설정하지 않은 나머지 경로
			 
			login 관련 설정
				loginPage(): 로그인 페이지 링크 설정
				defaultSuccessUrl() : 로그인 성공 후 리다이렉트할 주소
				logoutSuccessUrl() : 로그아웃 성공 후 리다이렉트할 주소
				invalidateHttpSession() : 로그아웃 이수 세션 전체 삭제 여부
			
		*/
		http.authorizeRequests()
				.antMatchers("/login", "/signup", "/user").permitAll() //누구나 접근
				.antMatchers("/").hasRole("USER") // USER, ADMIN만 접근
				.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근
				.anyRequest().authenticated() //나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
			.and()
				.formLogin()
					.loginPage("/login") //로그인 페이지 링크
					.defaultSuccessUrl("/") // 로그인 성공 후 리다이렌트 주소
			.and()
				.logout()	
					.logoutSuccessUrl("/login") //로그인 성공 시 리다이렉트 주소
					.invalidateHttpSession(true) // 세션 제거
		;
	}
	
	
	/*
	 * configure(AuthenticationManagerBuilder auth)
	 *	스프링시큐리티에서는 모든 인증은 AuthenticationManager를 통해 이루어지며 
	 *  AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용합니다.
	 *  즉, 인증을 위해서는 UserDetailService를 통해서 필요한 정보들을 가져옵니다.
	 */
	
	// 로그인 할 때 필요한 정보 가져오기
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
}
