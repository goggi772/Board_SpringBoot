package com.board.entity.board;

import com.board.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Getter
@EqualsAndHashCode( of = {"id"}) //equals와 hashcode 자동 생성 annotation
@NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자 셍상 annotation
public class User extends BaseTimeEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id //기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성 DB에게 위임
    private Long id;
    private String email;
    private String password;
    private LocalDateTime lastLoginTime;

    @Builder
    public User(Long id, String email, String password, LocalDateTime lastLoginTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }

    @Override //계정의 권한 목록
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> { return "등록할 권한";});
        return collection;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override //계정 만료
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override //계정 잠금
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override //비밀번호 만료
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override //계정 활성화
    public boolean isEnabled() {
        return false;
    }


}
