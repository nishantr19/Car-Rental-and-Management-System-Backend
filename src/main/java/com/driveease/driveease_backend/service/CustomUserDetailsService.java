package com.driveease.driveease_backend.service;


import com.driveease.driveease_backend.model.User;
import com.driveease.driveease_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name()));
        return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(authorities)
                .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false)
                .build();
    }
}