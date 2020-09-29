package com.tony.church.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class churchSecurityConfig extends WebSecurityConfigurerAdapter {

    // add a reference to our security data source

    @Autowired
    private DataSource securityDataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.   
		//securityContext(). // store the user after login
                csrf().disable().
                authorizeRequests()
                .antMatchers("/index").hasRole("MEMBER")
                .antMatchers("/events").hasRole("MEMBER")
                .antMatchers("/events/**").hasRole("MANAGER")
                .antMatchers("/events/**").hasRole("ADMIN")

                .antMatchers("/members").hasRole("MEMBER")
                .antMatchers("/members/details").hasRole("MEMBER")
                .antMatchers("/members/member/**").hasRole("MANAGER")
                .antMatchers("/members/member/**").hasRole("ADMIN")
		//.antMatchers("/members/member/**").hasAnyRole("ADMIN", "MANAGER") // combines the two above it

                .antMatchers("/members/**").hasRole("MANAGER")
                .antMatchers("/members/**").hasRole("ADMIN")

                .antMatchers("/send-email/**").hasRole("MANAGER")
                .antMatchers("/send-email/**").hasRole("ADMIN")

                .antMatchers("/users").hasRole("MEMBER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
		.and()
		.headers() // do not cache the page
		.and()
		.anonymous().principal("guest").authorities("ROLE_GUEST")// create guest user
									;

    }

}

