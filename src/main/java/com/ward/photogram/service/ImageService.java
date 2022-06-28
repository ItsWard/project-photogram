package com.ward.photogram.service;

import com.ward.photogram.domain.image.Image;
import com.ward.photogram.domain.image.ImageRepository;
import com.ward.photogram.web.config.auth.PrincipalDetaiils;
import com.ward.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") //properties에 있는 값을 내가 원하는 걸 가져올 수 있음
    private String uploadFolder;
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetaiils principalDetaiils){
        UUID uuid = UUID.randomUUID(); //사용자는 폴더에 어떤 파일이 있는지 모름, 동일한 이름으로 들어오면 덮어씌어지기 때문에 구분 할 수 있는 UUID 만듦
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();// 1.jpg 실제 파일 이름이 저장됨

        Path imgeFilePath = Paths.get(uploadFolder + imageFileName); //propreties에 있는 upload 폴더

        //(통신 , I/O) ->예외가 발생할 수 있음
        try {
            Files.write(imgeFilePath, imageUploadDto.getFile().getBytes()); //서버에 저장
        } catch (Exception e) {
            e.printStackTrace();
        }

        //image 테이블에 경로, 설명, 유저이름 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetaiils.getUser()); //실제 데이터는 properties의 경로에 적혀있으니까, 그 이름으로 찾으면됨
        //실제 경로를 찾기위해서,
        // 1. imgeFilePath가 아닌 imageFileName를 이용해서 찾아냄
        // 2. 어떤 유저가 이미지를 저장했는지를 알아야하기 때문에, 인증된 유저이름을 받아야함
        Image imageEntity = imageRepository.save(image);
        System.out.println(imageEntity);

    }
}
