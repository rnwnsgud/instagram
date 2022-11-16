package snsProject.photogram.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import snsProject.photogram.config.auth.PrincipalDetailsService;
import snsProject.photogram.config.oauth.OAuth2DetailsService;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
                    .anyRequest().permitAll()
                        .and()
                .formLogin()
                .loginPage("/auth/signin")
                .loginProcessingUrl("/auth/signin")
                .defaultSuccessUrl("/")
                    .and()
                .oauth2Login() // form 로그인도 하는데, oauth2로그인도 진행한다.
                .userInfoEndpoint() // oauth2 로그인을하면 회원정보 바로 받기 (code 받고 access 토큰 요청스킵)
                .userService(oAuth2DetailsService)
                    .and()
                .and().build();


    }






}
