package com.ward.photogram.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // IoC
public class PrincipalDetailsService implements UserDetailsService {


    //실제 로그인 설정을 시큐리티 설정파일에 Post방식으로 넣어두면,
    //시큐리티 설정파일 <-Post 되면
    //IoC 컨테이너의 UserDetailsService가 로그인을 진행함
    //현재 PrincipalDetailsService implements UserDetailsService하여서 기존의
    //UserDetailsService가 PrincipalDetailsService로 덮어씌어져 해당 프로그램으로 작동되는 구조임
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
