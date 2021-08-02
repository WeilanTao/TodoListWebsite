package com.taoemily.mytodo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
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

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isExpir(token));

    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isExpir(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }

}
