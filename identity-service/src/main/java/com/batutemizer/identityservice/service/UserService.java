package com.batutemizer.identityservice.service;

import com.batutemizer.identityservice.dto.request.UserCreateRequest;
import com.batutemizer.identityservice.dto.response.UserResponse;
import com.batutemizer.identityservice.entity.UserEntity;
import com.batutemizer.identityservice.enums.RoleEnum;
import com.batutemizer.identityservice.mapper.UserMapper;
import com.batutemizer.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {

        UserEntity entity = userMapper.toEntity(request);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        entity.setRole(RoleEnum.USER);
        UserEntity savedUser = userRepository.save(entity);
        return userMapper.toDTO(savedUser);
    }
}
