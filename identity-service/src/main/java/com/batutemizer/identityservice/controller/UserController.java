package com.batutemizer.identityservice.controller;

import com.batutemizer.identityservice.dto.request.UserCreateRequest;
import com.batutemizer.identityservice.dto.response.UserResponse;
import com.batutemizer.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest request){
        return userService.createUser(request);
    }
}
