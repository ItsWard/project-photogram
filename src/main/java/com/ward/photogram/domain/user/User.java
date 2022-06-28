package com.ward.photogram.domain.user;

//JPA Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공 ->DB로)

import com.ward.photogram.domain.image.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder //빌더 자동생성(User.builder().id("이원용")....
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@Getter
@Setter
@ToString // getter setter tostring 등 POJO와 bean에 관련된 재사용 코드 생성
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

    //양방향 매핑을 위한한
    //나는 해당 Entitu 객체(DB)에 등록된게 아니므로 테이블 칼럼 만들지말고
    // User를 Select할 때마다 User id로 등록된 image들을 모두 가져와
    //Lazy => User를 Select흘 때 User id로 등록된 image들을 가져오지마, 대신 getImages 함수의 image들이 호출될 떄 가지고옴
    //Eager => User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Image> images; //양방향 매핑

   private LocalDateTime createDate;

    @PrePersist // 데이터베이스에 Insert되기전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now(); // Insert된 현재시간 입력
    }


}
