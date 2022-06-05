package com.ward.photogram.service;


import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service //1.IoC 2트랜잭션 관리
public class AuthService {


    private final UserRepository userRepository; //final을 사용해야하는 이유 1. 컨틀로러 내부에서 서비스 객체를 바꿀 수 없음
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Security Config의 비밀번호
    @Transactional // Write(Insert, Update, Delete) //repository 필요 - DB
    public User 회원가입(User user){
        //회원가입 진행
        String rawPassword = user.getPassword(); //회원가입 시 들어오는 UserPassword
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //bCryptPasswordEncoder 로 비밀번호 보안
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
