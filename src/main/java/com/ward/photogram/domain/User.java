package com.ward.photogram.domain;

//JPA Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공 ->DB로)

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder //빌더 자동생성(User.builder().id("이원용")....
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@Data // getter setter tostring 등 POJO와 bean에 관련된 재사용 코드 생성
@Entity //데이터베이스에 테이블을 생성
public class User {

    @Id // primary Key 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증략 전략을 데이터베이스를 따라간다.
    private Long id;


    @Column(length = 15, unique = true) //제약조건 DB에 들어갈 때, 길이 제한 및 Unique
    private String username;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String password;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String email;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String name;

    private String website; //웹사이트
    private String bio; //자기소개
    private String phone;
    private String gender;

    private String profileImageUrl; //유저 사진
    private String role; //권한

    private LocalDateTime createDate;

    @PrePersist // 데이터베이스에 Insert되기전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now(); // Insert된 현재시간 입력
    }
}
