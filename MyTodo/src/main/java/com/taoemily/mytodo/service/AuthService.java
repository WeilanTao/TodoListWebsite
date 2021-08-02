package com.taoemily.mytodo.service;

import com.taoemily.mytodo.dto.LoginRequest;
import com.taoemily.mytodo.dto.LoginResponse;
import com.taoemily.mytodo.dto.SignupRequest;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.UserRepository;
import com.taoemily.mytodo.config.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public LoginResponse login(LoginRequest loginRequest){

        try {
            String email=loginRequest.getUserEmail();
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email,
                    loginRequest.getPassWord()));
//            SecurityContextHolder.getContext().setAuthentication(authenticate);

            String token = jwtUtil.generateToken(authenticate);

            LoginResponse loginResponse = new LoginResponse(token, userEntityService.getUserNameByEmail(email),email);
            return loginResponse;
        }catch(RuntimeException e){
            System.out.println("hihihihihihihihihi"+e);
            throw e;
        }
    }

    public UserEntity saveNewUser(SignupRequest signupRequest){
        String pw =  bcryptEncoder.encode(signupRequest.getPassword());

        UserEntity userEntity= new UserEntity(
                signupRequest.getUsername(),
                pw,
                signupRequest.getUseremail(),
                true);
        UserEntity savedUser = userRepository.saveAndFlush(userEntity);

        return savedUser;
    }
//$2a$10$pxL0nNjZ6PjD/tBNw1AC6.WXEWfGcKdR6S2Y/kIHPVoRmQyC8bSqa
//$2a$10$pxL0nNjZ6PjD/tBNw1AC6.WXEWfGcKdR6S2Y/kIHPVoRmQyC8bSqa
//$2a$10$6FTXxzAGIDL.Frcdl.dQjuz97VzIT/ic22CIKsAefT8kT71U4c2iK

}
