package com.work.gyeoljea.user.login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.work.gyeoljea.DTO.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserLoginController {
	private final UserService userService;
	
	@PostMapping("/user")
	public String signup(UserInfoDto infoDto) {
		userService.save(infoDto);
		return "redirect:/login";
	}
	
	
	/* 
	 * 로그아웃은은 Post Method로 요청해야 직접 로그아웃 경로를 지정해 줄 수 있다. 
	 * a 태그 를 이요하여 Get 방식으로 지정된 로그아웃 경로를 
	 * Get Method로 로그아웃 요청을 처리하기 위해서는
	 * SecurityContextLogoutHandler 사용
	 */
	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler()
			.logout(request,  response, 
					SecurityContextHolder
						.getContext()
						.getAuthentication());
		return "redirect:/login";
	}
	
}
