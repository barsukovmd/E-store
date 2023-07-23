package com.tms.estore.service.impl;

import com.tms.estore.domain.User;
import com.tms.estore.repository.UserRepository;
import com.tms.estore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getVerifyUser(String login, String email) {
        return userRepository.findUserByLoginOrEmail(login, email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
