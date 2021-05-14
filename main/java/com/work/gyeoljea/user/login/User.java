package com.work.gyeoljea.user.login;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/* JPA
 * @Entity
 * 테이블에 대응하는 하나의 클래스
 * 테이블과 자바 클래스가 매핑됨
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class User implements UserDetails{
	
	/*JPA
	 * @Id
	 * 	해당 컬럼이 PK 임을 의미
	 * 	모든 Entity에 반드시 지정해주어야 하는 것
	 * 	주로 @GeneratedValue 와 함께 식별키를 어떤 전략으로 생성하는 지 명시
	 * 
	 * @GeneratedValue
	 * 	strategy / generator	
	 * 	strategy
	 * 		AUTO: 특정 데이터베이스에 맞게 자동 생성
	 * 		TABLE: 별도의 키를 생성해주는 테이블 이용
	 *		SEQUNCE: 데이터베이스의 시퀀스를 이용해서 데이터베이스에 위임하는 방식
	 *	generator
	 *		@TableGenerator
	 *		@SequnceGenerator
	 *
	 *@Column
	 *	인스턴스 변수가 컬럼이 되기 때문에 컬럼명을 별도로 지정하거나 컬럼의 사이즈, 제약조건 추가하기 위해 사용
	 *
	 *@Table
	 *	클래스가 테이블이 되기 때문에 클래스의 선언부에 작성하여 테이블명을 어떻게 정할 것이지 결정
	 *	@Table이 지정되지 않으면, 클래스 명으로 테이블 생성
	 */		
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;

	@Column(name="email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name = "auth")
	private String auth;
	
	@Builder
	public User(String email, String password, String auth) {
		this.email = email;
		this.password = password;
		this.auth = auth;
	}

	
	/* 사용자의 권한. 자료형 GrantedAuthority 로 구현
	 * GrantedAuthority : 권한 객체. 이를 구현한 클래스 4가지 중 하나 SimpleGrantedAuthority
	 * SimpleGrantedAuthority 는 권한의 문자열 값을 파라미터에 전달해주면 권한 객체 생성해줌
	 * 즉 권한의 명칭만 저장하는 것
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		for(String role : auth.split(",")) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}

	// 사용자의 password 반환
	@Override
	public String getPassword() { 	
		return password;
	}

	// 사용자의 id 반환 ( Unique )
	@Override
	public String getUsername() { 
		return email;
	}

	// 계정 만료 여부
	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	// 계정 잠금 여부
	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	// 패스워드 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	// 계정 사용 가능 여부
	@Override
	public boolean isEnabled() {
		return true;
	}

}
