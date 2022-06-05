package com.ward.photogram.web.api;

import com.ward.photogram.config.auth.PrincipalDetaiils;
import com.ward.photogram.service.SubscribeService;
import com.ward.photogram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetaiils principalDetaiils, @PathVariable Long toUserId){
        subscribeService.구독하기(principalDetaiils.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공", null),HttpStatus.OK);
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetaiils principalDetaiils, @PathVariable Long toUserId){
        subscribeService.구독취소하기(principalDetaiils.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독취소하기 성공", null),HttpStatus.OK);
    }
}
