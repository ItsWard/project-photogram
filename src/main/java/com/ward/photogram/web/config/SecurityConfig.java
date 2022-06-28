package com.ward.photogram.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //web에서 제공하는 시큐리티를 사용합니다.
@Configuration //스프링 컨테이너에서 싱글톤(static)으로 Bean을 관리할 수 있도록 명시적으로 사용 + 객체를 하나만 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean //SecurityConfig가 Ioc에 등록 될 때 Bean 어노테이션을 읽어서 return new BCryptPasswordEncoder()를 가지고있어, DI에서 사용만 하면됨
    public BCryptPasswordEncoder encoded(){ //암호화 진행
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();


        http.authorizeRequests() //요청에 대한 권한을 지정 할 수 있음
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**") // 해당 Url로 진행되는 모든 것들
                .authenticated()
                .anyRequest()// 인증이 되어야지만 사용 가능함
                .permitAll()//접근을 전부 허용함
                .and()
                .formLogin()// 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용함
                .loginPage("/auth/signin")//로그인 페이지 해당 주소
                .loginProcessingUrl("/login")//POST -> 스프링 시큐리티가 로그인 프로세스 진행 (로그인 요청인지 확인)//UserDetailsService
                .defaultSuccessUrl("/"); //정상적으로 인증 성공했을경우 이동하는 페이지 설정
    }
}
