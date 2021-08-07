package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.RefreshToken;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refershtoken.expir.ms}")
    private Long refershtokenexpir;
    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String generateRefreshToken(String email){
        UserEntity userEntity = userEntityService.getUserByEmail(email);
        String refreshtoken = UUID.randomUUID().toString();
        Instant expiryDate= (Instant.now().plusMillis(refershtokenexpir));
        RefreshToken refreshToken= new RefreshToken(userEntity,refreshtoken,expiryDate);
        refreshTokenRepository.saveAndFlush(refreshToken);

        return refreshtoken;
    }

    public RefreshToken verifyRefreshToken(String payloadRefreshToken){
        //check the refreshtoken is in the database
        RefreshToken refreshToken= refreshTokenRepository.findByRefreshToken(payloadRefreshToken)
                .orElseThrow(()->new RuntimeException("No such token in database"));

        //check the expiry date of therefeshtoken; delete it if expires
        Instant instant = refreshToken.getExpiryDate();
//        String email =refreshToken.getUser().getEmail();
        if(instant.compareTo(Instant.now()) < 0){
            deleteRefreshToken(payloadRefreshToken);
            throw new RuntimeException("refresh token expires");
        }

        return refreshToken;
    }

    public void deleteRefreshToken(String token ){
        System.out.println("hihihi"+token);
        refreshTokenRepository.deleteByToken(token);
        refreshTokenRepository.flush();
    }

}
