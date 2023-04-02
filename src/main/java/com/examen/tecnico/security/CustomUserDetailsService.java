package com.examen.tecnico.security;

import com.examen.tecnico.enums.RoleUser;
import com.examen.tecnico.model.UserEntity;
import com.examen.tecnico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;
    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), authorities(Collections.singletonList(user.getRoles())));
    }

    private Collection<GrantedAuthority> authorities(Collection<RoleUser> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
                //Collections.singleton(new SimpleGrantedAuthority(roles.name()));

    }
}
