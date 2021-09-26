package org.yastech.minimal.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.yastech.minimal.data.PasswordEncoder
import org.yastech.minimal.services.UserDetailService


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
(
    prePostEnabled = true
)
class WebAppSecurity
(
    private var authTokenFilter: AuthTokenFilter,
    private var userDetailService: UserDetailService,
    private var unauthorizedHandler: AuthEntryPointJwt,
)
    : WebSecurityConfigurerAdapter()
{

    // HTTP SECURITY CONFIGURATION
    override fun configure(http: HttpSecurity)
    {
        http
            // CSRF CONFIG
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

            // ONLY SAME ORIGINS HEADER
            .and().headers().frameOptions().sameOrigin().and()

            // SET COOKIE SAME SITE POLICY
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // SET AUTH EXCEPTION HANDLER
            .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)

            // PERMISSION SETTING
            .and().authorizeRequests()

            // ACCESS TO CLIENT URLS
            .antMatchers("/*", "/account/*").permitAll()

            // ACCESS TO CLIENT RESOURCES
            .antMatchers("/css/*", "/img/*", "/js/*").permitAll()

            // ACCESS WEBSOCKET
            .antMatchers("/wst/**").permitAll()

            // ACCESS TO AUTH APIS AND CONTROLLERS
            .antMatchers("/api/rest/account/**").permitAll()

            .antMatchers("/api/controller/account/**").permitAll()

        // APPLY AUTH FILTER
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    // AUTHENTICATION CONFIGURATION
    override fun configure(auth: AuthenticationManagerBuilder)
    {
        // SET USER SERVICE
        auth.userDetailsService(userDetailService).passwordEncoder(PasswordEncoder().encoder())
    }

    // SETUP AUTHENTICATION MANAGER
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager
    {
        return super.authenticationManagerBean()
    }

    // SETUP AUTH FILTER
    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter
    {
        return authTokenFilter
    }
}