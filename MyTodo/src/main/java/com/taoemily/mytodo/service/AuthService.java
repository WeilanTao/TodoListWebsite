package com.taoemily.mytodo.service;

import com.taoemily.mytodo.dto.LoginRequest;
import com.taoemily.mytodo.dto.AuthResponse;
import com.taoemily.mytodo.dto.RefreshTokenRequest;
import com.taoemily.mytodo.dto.SignupRequest;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.UserRepository;
import com.taoemily.mytodo.config.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public AuthResponse login(LoginRequest loginRequest) {

        try {
            String email = loginRequest.getUserEmail();
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email,
                    loginRequest.getPassWord()));

            SecurityContextHolder.getContext().setAuthentication(authenticate);

            String accessToken = jwtUtil.generateToken(authenticate);

            UserEntity userEntity = userEntityService.getUserByEmail(email);

            String refreshToken= refreshTokenService.generateRefreshToken(email);

            AuthResponse authResponse = new AuthResponse(
                    accessToken,
                    userEntity.getUsername(),
                    userEntity.getEmail(),
                    refreshToken,
                    userEntity.getIsAdmin()?"ADMIN":"USER"
            );
            return authResponse;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public UserEntity saveNewUser(SignupRequest signupRequest) {
        String pw = bcryptEncoder.encode(signupRequest.getPassword());

        UserEntity userEntity = new UserEntity(
                signupRequest.getUsername(),
                pw,
                signupRequest.getUseremail(),
                false);
        UserEntity savedUser = userRepository.saveAndFlush(userEntity);

        return savedUser;
    }

    public AuthResponse generateRefreshToken(RefreshTokenRequest refreshTokenRequest){
        //get the refresh token from the payload
        String payloadRefreshToken= refreshTokenRequest.getRefreshtoken();
        try{
            //check if the refresh token is in the database; check if the refresh token is expired
            String email = refreshTokenService.verifyRefreshToken(payloadRefreshToken); //check the refresh token is in the database; check the refresh token is not expired and return the user email as a response if it's valid

            //if the refersh token is in the database && the refresh token is not expired, let's generate a new access token
            String newAccessToken = jwtUtil.generateTokenByEmail(email);

            System.out.println(SecurityContextHolder.getContext().toString());

            //return the response with a new accesstoken and the refresh token from the payload
            return new AuthResponse(

                    newAccessToken,
                    "testusername",
                    "testemail",
                     payloadRefreshToken,
                    "testrole");

        }catch(RuntimeException exception){
            //TODO exception: what if the token is not in database
            throw new RuntimeException("refresh token is not valid");
        }

    }

    public void deleteRefreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshtoken());
        //should I clean the securitycontextholder? logout 后 再refreshtoken ； todo 看看会不会打印出来东西

    }

}
