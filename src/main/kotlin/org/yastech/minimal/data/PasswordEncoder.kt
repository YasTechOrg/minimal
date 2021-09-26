package org.yastech.minimal.data

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoder
{

    @Bean
    fun encoder(): PasswordEncoder
    {
        return BCryptPasswordEncoder(10)
    }
}