package com.tms.estore.service;

import com.tms.estore.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByLogin(String login);

    void addUser(User user);

    Optional<User> getVerifyUser(String login, String email);

    Optional<User> getUserById(Long id);
}
