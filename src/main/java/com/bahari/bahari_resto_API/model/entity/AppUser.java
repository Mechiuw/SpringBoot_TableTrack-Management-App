package com.bahari.bahari_resto_API.model.entity;


import com.bahari.bahari_resto_API.constant.ERole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.plaf.basic.BasicListUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
    private String id;
    private String username;
    @Getter
    private String password;
    private ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.name()));
        return simpleGrantedAuthorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
