package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service        // 1.IOC  2.트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    public User 회원가입(User user){    // 회원가입 진행
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
