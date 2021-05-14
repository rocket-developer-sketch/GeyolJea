package com.work.gyeoljea.user.login;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


/* 
 * Lombok의 @RequiredArgsConstructor 로 의존성 주입하기
 * 	초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 대해 생성자를 생성해줌.
 * 	이 어노테이션을 붙이지 않으면, UserRepository 에서 final 변수를 초기화하라는 에러가 발생.
 */

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException{
		// 기본 반환 타입 UserDetails 대신 자료형 User 반환
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException((email)));
		
	}
}
