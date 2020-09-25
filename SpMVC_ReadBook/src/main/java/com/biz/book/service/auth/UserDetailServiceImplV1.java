package com.biz.book.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.biz.book.mapper.AuthorityDao;
import com.biz.book.mapper.UserDao;
import com.biz.book.model.UserDetailsVO;

import lombok.RequiredArgsConstructor;

/*
 * 
 * spring security 프로젝트에서 
 * 사용자 인가와 권한을 관리하는 클래스
 * 이클립스에 내장되어있는 인터페이스 
 * UserDetailsService 를 Customizing 커스터마이징
 * 
 * Customizing?(=사용자화)
 * 패키지형 솔루션을 가지고 있는 it  회사에서
 * 어떤 회사에 솔루션을 판매하면서
 * 회사의 실정, 업무환경, 여러가지 여건들을 요구분석하여
 * 솔루션을 사용하는 회사에 최적화 하는 것
 */

@RequiredArgsConstructor
public class UserDetailServiceImplV1 implements UserDetailsService {
	/*
	 *  그동안 @Autowired를 사용하여 객체를 주입받아서 사용해 왔는데
	 *  @Autowired로 주입받은 객체에 메모리 누수현상이 발생을 하더라
	 *  
	 *  주입받을 객체를 final로 선언을 해주는데
	 *  final로 선언한 객체는 반드시 생성자에서 객체 초기화(주입)을 해야한다.
	 *  
	 *  1. 주입받을 객체를 final로 선언하고
	 *  2. 생성자의 매개변수를 통일하여 객체를 초기화 한다.
	 *  3. 주입받을 객체의 개수가 늘거나 줄면 생성자를 또다시 변경해야하는
	 *  번거로움이 있다.
	 *  
	 *  
	 *  lombok의 @RequiredArgsConstructor 를사용하면
	 *  final로 선언된 모든 필드변수들을 모아서 생성자로 만들어준다.
	 */
	
	
//	public UserDetailServiceImplV1(UserDao userDao, AuthorityDao authDao) {
//		this.userDao = userDao;
//		this.authDao =authDao;
//		
//		
//		
//	} 
	
	
	
	
	/*
	 * 이프로젝트에서 사용할 memver(user)관련 table에서 username으로
	 * 사용자 정보를 SELECT 하고
	 * 사용자의 ROLL 정보를 기준으로 사용자의 권한을 설정하여
	 * 기능을 수행을 제한하는 설정을 하고 
	 * 사용자의 여러 세부 정보를 VO객체에 담아주는 역할 수행 
	 */
	
	private final UserDao userDao;
	private final AuthorityDao authDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetailsVO userDetail = userDao.findById(username);
		if(userDetail == null) {
			
			throw new UsernameNotFoundException(username+" 정보를 찾을수가 없습니다. ");
			
		}
		
		
		return userDetail;
	}

}