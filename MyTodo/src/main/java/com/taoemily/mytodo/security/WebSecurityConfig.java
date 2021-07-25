//package com.taoemily.mytodo.security;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
////@EnableWebSecurity
////@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * User detail service
//     * @return
//     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    /**
//     * Security interceptor
//     * @param http
//     * @throws Exception
//     */
//
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .httpBasic()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/", "/mytodo", "/login").permitAll()
//                    .antMatchers("/admin").hasRole("ADMIN")
//                    .antMatchers("/users").hasAnyRole("ADMIN","USER")
//                    .anyRequest().authenticated()
//            ;
//        }
//
//
//
//
//}
