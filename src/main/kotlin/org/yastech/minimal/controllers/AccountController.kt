package org.yastech.minimal.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.yastech.minimal.data.HexConvertor
import org.yastech.minimal.data.PasswordEncoder
import org.yastech.minimal.data.USERS
import org.yastech.minimal.security.JWTUtils
import org.yastech.minimal.security.UserAuthenticator
import org.yastech.minimal.services.EmailService
import org.yastech.minimal.services.UserService
import org.yastech.minimal.tables.User
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random


@Controller
@RequestMapping("/api/controller/account")
class AccountController
(
    private var userService: UserService,
    private var passwordEncoder: PasswordEncoder,
    private var userAuthenticator: UserAuthenticator,
    private var jwtUtils: JWTUtils,
    private var emailService: EmailService,
    private var hexConvertor: HexConvertor,
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
    fun register(@ModelAttribute("user") user: User, bindingResult: BindingResult, httpServletRequest: HttpServletRequest): String
    {
        return if (!userService.exist(user.email))
        {
            val code = hexConvertor.encode("verify-${user.email}-${ Random.nextInt(100, 1000000) }")

            user.confirmCode = code
            user.role = USERS.NORMAL_USER

            emailService.sendRegisterEmail(user.email, code, httpServletRequest)

            userService.add(user)

            "redirect:/account/login?res=d_reg"
        }
        else
        {
            "redirect:/account/register?res=exi"
        }
    }

    @GetMapping("/confirm")
    fun confirm(@RequestParam code: String): String
    {
        val username = hexConvertor.decode(code).split("-")[1]

        return if (userService.exist(username))
        {
            if (userService.get(username).confirmCode == code)
            {
                val user = userService.get(username)

                user.accepted = true

                userService.update(user)

                "redirect:/account/login?res=c_done"
            }
            else
            {
                "redirect:/account/login?res=c_err"
            }
        }
        else
        {
            "redirect:/account/login?res=c_err"
        }
    }
}