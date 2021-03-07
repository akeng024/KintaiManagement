package com.akeng.kintaimanagement.auth;

import com.akeng.kintaimanagement.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {
    @Getter
    private User user;

    public SimpleLoginUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getEnable(), true, true,
                true, convertGrantedAuthorities(user.getRole()));
        this.user = user;
    }

    static Set<GrantedAuthority> convertGrantedAuthorities(String role) {
        if (StringUtils.isEmpty(role)) {
            return Collections.emptySet();
        }
        Set<GrantedAuthority> authorities = Stream.of(role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return authorities;
    }
}
