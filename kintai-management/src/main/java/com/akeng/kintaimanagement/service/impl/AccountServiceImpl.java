package com.akeng.kintaimanagement.service.impl;

import com.akeng.kintaimanagement.model.User;
import com.akeng.kintaimanagement.repository.UserRepository;
import com.akeng.kintaimanagement.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void register(String name, String email, String password, String role) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("invalid name or email");
        }
        String encodedPassword = passwordEncode(password);
        log.debug("name:{}, email:{}, role:{}", name, email, role);
        User user = new User(null, name, email, encodedPassword, role, Boolean.TRUE);
        userRepository.saveAndFlush(user);
    }

    /**
     *
     * @param rawPassword 平文のパスワード
     * @return 暗号化されたパスワード
     */
    private String passwordEncode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}