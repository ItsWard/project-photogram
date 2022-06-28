package com.ward.photogram.web.dto.image;

import com.ward.photogram.domain.image.Image;
import com.ward.photogram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    //NotBlank 불가능
    private MultipartFile file; //이미지 데이터
    private String caption; //파일에 대한 설명

    public Image toEntity(String postImageUrl, User user){ //UUID도 함께 붙어있어서, 추가가 불가능하므로
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user) // 어떤 유저가 넣었는지를 알아야하기 때문에
                .build();
    }
}
