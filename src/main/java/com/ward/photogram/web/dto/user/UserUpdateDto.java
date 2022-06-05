package com.ward.photogram.web.dto.user;

import com.ward.photogram.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name; //필수
    @NotBlank
    private String password;//필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    //조금 위험함 코드수정이 필요할 예정 - null값 어떻게 처리?
    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
