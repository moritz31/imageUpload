package de.darmotek.imageUpload;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests().antMatchers("/console/**").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/", "/home", "/login", "/upload", "/post").permitAll()
                    .anyRequest().authenticated()
            .and().csrf()
                    .disable()
                    .headers().frameOptions().disable();
        }



}
