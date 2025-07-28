package com.nelson.fileupload.security;

import com.nelson.fileupload.entity.User;
import com.nelson.fileupload.exception.UserNotFoundException;
import com.nelson.fileupload.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());

        Set<GrantedAuthority> authorities = Set.of();

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }

}
