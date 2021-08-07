package com.taoemily.mytodo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JwtUtil {

    @Value("${jwt.accesstoken.expir.ms}")
    private Long accesstokenexpir;

    private static final String SECRET_KEY = "mytodo123web321java123321springbootemilyweilantao" +
            "123456789987654321emilycodingmytodowebsitehappyhourdsjakldjsakl";


    public String generateToken(Authentication authenticate) {
        Object principal = authenticate.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        System.out.println(accesstokenexpir);
        return
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(subject)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .setExpiration(new Date(System.currentTimeMillis() + accesstokenexpir))
                        .compact();
    }

    public String generateTokenByEmail(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accesstokenexpir))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * I treat email as the UserDetails.username!
     *
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            String email = extractUserEmail(token);
            return (StringUtils.hasText(email) && email.equals(userDetails.getUsername()) && !isExpir(token));
        } catch (RuntimeException e) {
            log.error(e.toString());
        }
        return false;
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isExpir(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }


}
