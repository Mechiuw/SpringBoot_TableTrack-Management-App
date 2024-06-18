package com.bahari.bahari_resto_API.security.service;

import com.bahari.bahari_resto_API.model.entity.UserEntity;
import com.bahari.bahari_resto_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username not found %s",username)));

        GrantedAuthority authority = new SimpleGrantedAuthority(String.format("ROLE_%s",user.getRole()));
        return new User(user.getUsername(),user.getPassword(), Collections.singleton(authority));
    }
}
