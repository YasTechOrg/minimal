package org.yastech.minimal.services

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime.now
import javax.servlet.http.HttpServletRequest

@Service
class EmailService(private val javaMailSender: JavaMailSender)
{
    fun sendRegisterEmail(to: String, code: String, baseUrl: HttpServletRequest)
    {
        val msg = javaMailSender.createMimeMessage()

        val helper = MimeMessageHelper(msg, true)

        helper.setFrom("service@minimal.yastech.org")

        helper.setTo(to)

        helper.setSubject("Minimal - Verify Your Account")

        val url = ServletUriComponentsBuilder.fromRequestUri(baseUrl).replacePath(null).build().toUriString() +
                "/api/controller/account/confirm?code=$code"

        helper.setText("<h1>Minimal - Verify account email</h1><hr><p>Hi ${ to }!, Welcome to Minimal. You must verify your account before you can log in. Click on the link below to verify your account<br><br>Verify link : <a href='${ url }'>${ url }</a><br><br>Copyright ${ now().year } All Right Reserved | <a href='https://yastech.org'>YasTech</a></p>", true)

        javaMailSender.send(msg)
    }
}