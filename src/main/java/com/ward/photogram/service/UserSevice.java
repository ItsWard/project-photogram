package com.ward.photogram.service;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import com.ward.photogram.handler.ex.CustomValidationApiException;
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
//        User userEntity = userRepository.findById(id).get(); //get으로 진행하는 경우, null값이 들어오면 문제가 생김
        User userEntity = userRepository.findById(id).orElseThrow(() -> {return new CustomValidationApiException("찾을 수 없는 ID 입니다.");});
        //Exception을 발동시키기 위해서는 orElseTrow() 사용

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
