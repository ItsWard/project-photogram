package com.ward.photogram.handler;

import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice //모든 Exception을 가져올 수 있는 어노테이션
public class ControllerExceptionHandler {

//    @ExceptionHandler(CustomValidationException.class) //Runtime Excption에 해당하는 부분은 모두 여기로 옴옴
//   public CMRespDto<?> vaildationException(CustomValidationException e) {
//        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()); //CMRespDto => hash맵 저장, json형식으로 UX 좋지않음
//    } //Ajax 통신 , Android 에서는 해당 방법을 사용

    @ExceptionHandler(CustomValidationException.class) //웹에서는 이게 더 좋음
    public String vaildationException(CustomValidationException e) {
        System.out.println("--------------------------");
        return Script.back(e.getErrorMap().toString()); // => 오류메세지 팝업 및 자동으로 뒤로가짐
    }

}
