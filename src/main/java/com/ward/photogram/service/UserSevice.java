package com.ward.photogram.service;

import com.ward.photogram.domain.User;
import com.ward.photogram.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSevice {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public User 회원수정(Long id, User user) {
        //1. 영속화
        User userEntity = userRepository.findById(id).get();

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());//1.무조건 찾으면 get() //2. 못찾으면 exception throw-> orElseThrow

        return userEntity;
    }//2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)

}
