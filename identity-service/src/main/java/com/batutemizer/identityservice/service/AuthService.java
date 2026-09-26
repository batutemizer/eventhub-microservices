package com.batutemizer.identityservice.service;

import com.batutemizer.identityservice.dto.request.LoginRequest;
import com.batutemizer.identityservice.dto.response.LoginResponse;
import com.batutemizer.identityservice.entity.UserEntity;
import com.batutemizer.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository  userRepository;

    public LoginResponse login(LoginRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        Authentication authenticated = authenticationManager.authenticate(token);

        UserEntity user= userRepository.findByEmail(authenticated.getName()).orElseThrow();

        LoginResponse response = new LoginResponse();
        response.setUsername(authenticated.getName());

        String jwt = jwtService.generateToken(authenticated, user.getId());
        response.setToken(jwt);

        return  response;

    }

}
