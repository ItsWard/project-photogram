package com.ward.photogram.domain;

import org.springframework.data.jpa.repository.JpaRepository;


//어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 됨.
public interface UserRepository extends JpaRepository<User, Integer> {


}
