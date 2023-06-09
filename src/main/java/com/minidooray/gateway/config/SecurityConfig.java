package com.minidooray.gateway.config;

import com.minidooray.gateway.account.handler.LoginSuccessHandler;
import com.minidooray.gateway.account.service.OAuthLoginSuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthLoginSuccessService oAuthLoginSuccessService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                    .antMatchers("/signup").permitAll()
                    .antMatchers("/signup/submit").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .successHandler(loginSuccessHandler(null))
                    .and()
                .oauth2Login()
//                    .clientRegistrationRepository(clientRegistrationRepository())
//                    .authorizedClientService(auth2AuthorizedClientService())
                    .successHandler(loginSuccessHandler(null))
                    .userInfoEndpoint().userService(oAuthLoginSuccessService).and()
                    .and()
                .logout()
                    .and()
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionFixation()
                         .none()
                    .and()
                .build();
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(github());
//    }

//    @Bean
//    public OAuth2AuthorizedClientService auth2AuthorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }

//    private ClientRegistration github() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .userNameAttributeName("name")
//                .clientId("df5cbabe42b951bf6043")
//                .clientSecret("a071098f7eb7e894335ed593585473427fb6b812")
//                .build();
//    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        return new LoginSuccessHandler(redisTemplate);
    }

//    @Bean
//    public OAuthLoginSuccessService oAuthLoginSuccessService() {
//        return new OAuthLoginSuccessService;
//    }
}
