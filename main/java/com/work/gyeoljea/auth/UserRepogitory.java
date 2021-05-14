package com.work.gyeoljea.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/*
 * https://javaengine.tistory.com/entry/JPA-%EC%82%AC%EC%9A%A9%EB%B2%95-JpaRepository 
 * JpaRepository
 * 	스프링부터에서 Entity의 기본적인 CRUD 가능하도록 하는 인터페이스
 * 	@Repository등의 어노테이션을 추가할 필요 없음
 * 	JpaRepository 상속 받을 때는 사용될 Entity 클래스와 ID 값이 들어감
 * 	제공하는 기능은
 * 		save(): 레코드 저장(insert, update)
 * 		findOne(): PK로 레코드 한 건 찾기
 *		findAll(): 전체 레코드 불러오기. 정렬(sort), 페이징(pageable) 가능 
 * 		count(): 레코드 개수
 * 		delete(): 레코드 삭제
 * 
 * 	Query 메소드를 추가하여 작업할 내용을 스프링에게 알릴 수 있는데,
 * 	작성 규칙은 다음과 같다.
 * 		findBy(Entity 필드 이름): Query 요청 하는 메서드 임을 알림
 * 		countBy(Entity 필드 이름): Query 결과 레코드 수를 요청하는 메서드 임을 알림
 * 	Query 메소드에는 포함할 수 있는 키워드 또한 있는 데 아래와 같다.
 * 		And: 여러 필드를 and 로 검색 
 * 			findByEmailAndUserId(String email, String userId)
 * 		Or: 여러 필드를 or 로 검색
 * 			findByEmailOrUserId(String email, String userId)
 * 		Between: 필드의 두 값 사이에 있는 항목 검색
 * 			findByCreatedAtBetween(Date fromDate, Date toDate)
 * 		LessThan: 작은 항목 검색
 * 			findByAgeLessThanEqual(int age)
 * 		GreateThanEqual: 크거나 같은 항목 검색
 * 			findByAgeGraterThanEqual(int age)
 * 		Like: like 검색
 * 			findByNameLike(String name)
 * 		IsNull: null 항목 검색
 * 			findByJobIsNull()
 * 		In: 여러 값중에 하나인 항목 검색
 * 			findByJob(String … jobs)
 * 		OrderBy: 검색 결과를 정렬하여 전달
 * 			findByEmailOrderByNameAsc(String email)
 * 
 * 		자세한 정보는 
 * 			http://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.sample-app.finders.strategies
 * 
 */

/* 자바의 Optional 클래스
 * 	Wrapper Class의 하나
 * 	모든 타입의 참조 변수를 저장할 수 있음
 * 	예상치 못한 NullPointerException 예외를 제공하는 메소드로 간단히 회피할 수 있음
 * 	 == 복잡한 조건문 없이 null 값으로 인해 발생하는 예외를 처리할 수 있게 됨
 * 
 */
public interface UserRepogitory extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
