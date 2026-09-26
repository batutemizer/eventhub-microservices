package com.batutemizer.identityservice.mapper;

import com.batutemizer.identityservice.dto.request.UserCreateRequest;
import com.batutemizer.identityservice.dto.response.UserResponse;
import com.batutemizer.identityservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserCreateRequest request){
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public UserResponse toDTO(UserEntity user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
