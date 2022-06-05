package com.ward.photogram.web.dto.auth;
//Data Trans Object : 통신할 때 필요한 데이터를 담아두는 것, 데이터 교환을 위해 사용하는 객체(java Bean)

import com.ward.photogram.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data //Getter Setter
public class SignupDto {

    @Size(min = 2, max = 15)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();

    }

}
