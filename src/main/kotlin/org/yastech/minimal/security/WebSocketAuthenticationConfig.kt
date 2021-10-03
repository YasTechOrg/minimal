package org.yastech.minimal.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.yastech.minimal.services.UserDetailService


@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
class WebSocketAuthenticationConfig
(
    private var jwtUtils: JWTUtils,
    private var userDetailsService: UserDetailService
)
    : WebSocketMessageBrokerConfigurer
{
    override fun configureClientInboundChannel(registration: ChannelRegistration)
    {
        registration.interceptors(object : ChannelInterceptor
        {
            override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>
            {
                val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)!!

                if (StompCommand.SUBSCRIBE == accessor.command)
                {
                    val authorization = accessor.getNativeHeader("Authorization")

                    if (!authorization.isNullOrEmpty())
                    {
                        val accessToken = authorization[0].split(" ").toTypedArray()[1]

                        val username: String = jwtUtils.getUserNameFromJwtToken(accessToken)

                        val userDetails = userDetailsService.loadUserByUsername(username)

                        val authentication = UsernamePasswordAuthenticationToken(
                            userDetails, null,
                            userDetails.authorities
                        )

                        accessor.user = authentication
                    }
                }
                return message
            }
        })
    }
}