package com.ward.photogram.handler;

import com.ward.photogram.handler.ex.CustomApiException;
import com.ward.photogram.handler.ex.CustomValidationApiException;
import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.util.Script;
import com.ward.photogram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

    @ExceptionHandler(CustomValidationException.class) //웹에서는 이게 더 좋음(JSP 응답)
    public String validationException(CustomValidationException e) {
        System.out.println("--------------------------");
        return Script.back(e.getErrorMap().toString()); // => 오류메세지 팝업 및 자동으로 뒤로가짐
    }

    @ExceptionHandler(CustomValidationApiException.class) //데이터 응답 , 유효성 검사
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        System.out.println("--------------------------");
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class) //데이터 응답 , 유효성 검사
    public ResponseEntity<?> validationApiException(CustomApiException e) {
        System.out.println("--------------------------");
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){

        System.out.println(e.getSupportedHttpMethods().toString());
        System.out.println(e.getSupportedMethods().toString());
        System.out.println(e.getMessage().toString());
        System.out.println(e.getMethod().toString());
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public void ClassNotFoundException(ClassNotFoundException e){

        System.out.println(e.getCause().toString());
        System.out.println(e.getMessage().toString());
    }



}
