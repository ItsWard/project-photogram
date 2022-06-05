package com.ward.photogram.web;

import com.ward.photogram.config.auth.PrincipalDetaiils;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    
   @GetMapping("/user/{id}")
   public String profile(@PathVariable int id) {

       return "user/profile";
   }

   @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetaiils principalDetails) { //세션정보
       System.out.println("세션정보 :" + principalDetails.getUser());

       return "user/update";
    }
    
}
