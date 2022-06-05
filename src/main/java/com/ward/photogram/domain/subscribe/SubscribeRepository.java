package com.ward.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying //데이터베이스의 변경을 진행하는 (INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션이 필요함
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId); //성공하면 <변경된 행의 갯수>, 실패하면 -1(오류) 리턴

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId =:fromUserId AND toUserId= :toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId);
}
