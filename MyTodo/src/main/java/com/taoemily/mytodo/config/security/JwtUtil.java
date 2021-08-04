package com.taoemily.mytodo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil implements Serializable {
    private static final String SECRET_KEY="mytodo123web321java123321springbootemilyweilantao" +
            "123456789987654321emilycodingmytodowebsitehappyhourdsjakldjsakl";


    public static String generateToken(Authentication  authenticate  ) {
        Object principal= authenticate.getPrincipal();
        UserDetails userDetails= (UserDetails) principal;
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject) {
//        Date expir= new Date(System.currentTimeMillis()+3600000L * 48);
        Date expir = new Date(System.currentTimeMillis() + 300000); //5min

        return
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(subject)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .setExpiration(expir)
                        .compact();
    }

    public static String generateTokenByEmail(String subject){
        Date refreshRexpir= new Date(System.currentTimeMillis() + 600000); //10min

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(refreshRexpir)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * I treat email as the UserDetails.username!
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
       try {
           String email = extractUserEmail(token);
           return (StringUtils.hasText(email) && email.equals(userDetails.getUsername()) && !isExpir(token));
       }catch (RuntimeException e){
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
