package com.ward.photogram.config.auth;

import com.ward.photogram.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetaiils implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public PrincipalDetaiils(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //권한

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {return user.getRole();});

//        collector.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return user.getRole();
//            }
//        });

        //return collector; //권한이 (한개가 아닐 수 있음)
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 현재 이 계정이 만료되었는지, false일 경우 작동안됨
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 현재 이 계정이 잠겼는지, false일 경우 작동안됨
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 현재 이 계정의 비밀번호가 1년이 지난게아닌지? false일 경우 작동안됨
    }

    @Override
    public boolean isEnabled() {
        return true; // 현재 이 계정이 활성화 되어있는지, false일 경우 작동안됨
    }
}
