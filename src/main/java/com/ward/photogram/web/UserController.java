package com.ward.photogram.web;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.service.UserSevice;
import com.ward.photogram.web.config.auth.PrincipalDetaiils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserSevice userSevice;

   @GetMapping("/user/{id}")
   public String profile(@PathVariable Long id, Model model) {
       User userEntity = userSevice.회원프로필(id);
       model.addAttribute("user", userEntity);
       //user 프로필에는 user 정보, Image 정보, 구독 정보를 가져가야함
       //
       return "user/profile";
   }

   @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetaiils principalDetails) { //세션정보
       System.out.println("세션정보 :" + principalDetails.getUser());

       return "user/update";
    }
    
}
