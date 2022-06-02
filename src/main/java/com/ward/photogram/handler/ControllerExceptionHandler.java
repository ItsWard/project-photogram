package com.ward.photogram.handler;

import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice //모든 Exception을 가져올 수 있는 어노테이션
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) //Runtime Excption에 해당하는 부분은 모두 여기로 옴옴
   public CMRespDto<?> vaildationException(CustomValidationException e) {
        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
    }

}
