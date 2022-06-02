package vn.alpaca.demo.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import vn.alpaca.demo.Oauth2.CustomOAuth2UserService;
import vn.alpaca.demo.Services.UserService;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuthUserService;
    private final UserService userService;
    @Autowired
    public WebSecurityConfig(CustomOAuth2UserService customOAuthUserService, UserService userService) {
        this.customOAuthUserService = customOAuthUserService;
        this.userService = userService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //PERMISSION
                .authorizeRequests()
                    .antMatchers("/", "/store/**", "/product/**", "/login", "/oauth/**", "/register").permitAll() //ANY ROLE
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    //.antMatchers("/store/**").hasAnyAuthority("STORE")
                    .anyRequest().authenticated()

                //LOGIN WITH LOCAL USER
                .and()
                    .formLogin()
                    .defaultSuccessUrl("/")
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()

                //LOGIN WITH GOOGLE
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/")
                    .loginPage("/login")
                    .userInfoEndpoint()
                    .userService(customOAuthUserService);
    }
}
