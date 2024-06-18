package com.bahari.bahari_resto_API.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final Map<String,UserDetails> users = new HashMap<>();
    public MyUserDetailsService(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        users.put("user",User.builder()
                        .username("user")
                        .password(encoder.encode("user"))
                        .roles("USER")
                .build());
        users.put("admin",User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("not found user with username : %s",username));
        }
        return user;
    }
}
