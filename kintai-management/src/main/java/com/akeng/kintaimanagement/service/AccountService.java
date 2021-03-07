package com.akeng.kintaimanagement.service;

import com.akeng.kintaimanagement.model.User;

import java.util.List;

public interface AccountService {
    List<User> findAll();
    void register(String name, String email, String password, String role);
}
