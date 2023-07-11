package com.board.entity.repository;

import com.board.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

//    static final String UPDATE_USER_LAST_LOGIN = "UPDATE member SET LAST_LOGIN_TIME = :lastLoginTime WHERE EMAIL = :email";
//
//    @Transactional //트랜잭션 설정(에러발생시 DB rollback)
//    @Modifying //DB 테이블 변경하는 쿼리 작성시 적용해야 하는 annotation
//    @Query(value = "UPDATE_USER_LAST_LOGIN", nativeQuery = true) //일반 sql쿼리 적용s
//    public int updateUserLastLogin(@Param("email") String email, @Param("lastLoginTime") LocalDateTime lastLoginTime);
}
