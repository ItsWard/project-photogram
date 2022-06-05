package com.ward.photogram.web.api;

import com.ward.photogram.config.auth.PrincipalDetaiils;
import com.ward.photogram.domain.User;
import com.ward.photogram.service.UserSevice;
import com.ward.photogram.web.dto.CMRespDto;
import com.ward.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //데이터 응답
public class UserApiController {

    private final UserSevice userSevice;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable Long id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetaiils principalDetaiils) {

//        System.out.println(id);
//        System.out.println(userUpdateDto);
        User userEntity = userSevice.회원수정(id, userUpdateDto.toEntity());
        principalDetaiils.setUser(userEntity); //세션 업데이트 추가
        return new CMRespDto<>(1,"회원수정완료", userEntity);
    }

}
