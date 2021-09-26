package org.yastech.minimal.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.yastech.minimal.data.PasswordEncoder
import org.yastech.minimal.security.JWTUtils
import org.yastech.minimal.security.UserAuthenticator
import org.yastech.minimal.services.UserService
import org.yastech.minimal.tables.User


@Controller
@RequestMapping("/api/controller/account")
class AccountController
(
    private var userService: UserService,
    private var passwordEncoder: PasswordEncoder,
    private var userAuthenticator: UserAuthenticator,
    private var jwtUtils: JWTUtils,
)
{
    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam password: String): String
    {
        return if (userService.exist(email))
        {
            if (!userService.get(email).forgot!!)
            {
                if (passwordEncoder.encoder().matches(password, userService.get(email).password))
                {
                    if (userService.get(email).accepted!!)
                    {
                        val authentication = userAuthenticator.getAuthenticate(email, password)

                        SecurityContextHolder.getContext().authentication = authentication

                        val token = jwtUtils.generateJwtToken(authentication)

                        "redirect:/dashboard?at=$token"
                    }
                    else
                    {
                        "redirect:/account/login?res=acp"
                    }
                }
                else
                {
                    "redirect:/account/login?res=err"
                }
            }
            else
            {
                "redirect:/account/login?res=safe"
            }
        }
        else
        {
            "redirect:/account/login?res=err"
        }
    }

    @PostMapping("/register")
    fun register(@ModelAttribute("user") user: User, bindingResult: BindingResult): String
    {
        return if (!userService.exist(user.email))
        {
            userService.add(user)

            "redirect:/account/login?res=d_reg"
        }
        else
        {
            "redirect:/account/register?res=exi"
        }
    }
}