package com.taoemily.mytodo.service;

import com.taoemily.mytodo.dto.SignupRequest;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor

public class JwtUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    /**
     * Here the usermame is email
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> getUserEntityOptional = userRepository.getUserByEmail(email);

        UserEntity userEntity = getUserEntityOptional.orElseThrow(
                () -> new UsernameNotFoundException("No user found with email : " + email));

        String role = userEntity.getIsAdmin()? "ADMIN":"USER";

        UserDetails userDetails =
                User.withUsername(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(getAuthorities(role))
                        .build();
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

}
