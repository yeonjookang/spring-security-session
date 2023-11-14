package com.spring_security.project.service.impl;

import com.spring_security.project.domain.Account;
import com.spring_security.project.repository.UserRepository;
import com.spring_security.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
