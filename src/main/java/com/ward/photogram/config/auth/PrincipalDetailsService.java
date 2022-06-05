package com.ward.photogram.config.auth;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service // IoC
public class PrincipalDetailsService implements UserDetailsService {


    //실제 로그인 설정을 시큐리티 설정파일에 Post방식으로 넣어두면,
    //시큐리티 설정파일 <-Post 되면
    //IoC 컨테이너의 UserDetailsService가 로그인을 진행함
    //현재 PrincipalDetailsService implements UserDetailsService하여서 기존의
    //UserDetailsService가 PrincipalDetailsService로 덮어씌어져 해당 프로그램으로 작동되는 구조임

    private final UserRepository userRepository;

    //1. 패스워드는 시큐리티가 알아서 체킹하므로 신경쓸 필요 가 없음
    //2. 리턴이 정상적이면 PrincipalDetaiils 세션을 자동으로 만들어냄
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //userName만 있으면, 시큐리티가 알아서 처리를 해줌
        //사용하려면 User Repository가 필요함
//        System.out.println("실행되나요? " + username);
        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null)
            return null;
        else
            return new PrincipalDetaiils(userEntity);


    }
}
