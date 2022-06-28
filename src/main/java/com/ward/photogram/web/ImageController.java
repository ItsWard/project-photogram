package com.ward.photogram.web;

import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.service.ImageService;
import com.ward.photogram.web.config.auth.PrincipalDetaiils;
import com.ward.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {

        return "image/story";
    }
    
    @GetMapping("/image/popular")
    public String popular() {
        
        return "image/popular";
    }
    
    @GetMapping("/image/upload")
    public String upload() {

        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetaiils principalDetaiils) {

        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지않았습니다.", null); //요청 -> 데이터 X / 페이지 O
            //Error 맵은 날리지 않아도 괜찮음
        }


        imageService.imageUpload(imageUploadDto, principalDetaiils);
        return "redirect:/user/" + principalDetaiils.getUser().getId();
    }


}
