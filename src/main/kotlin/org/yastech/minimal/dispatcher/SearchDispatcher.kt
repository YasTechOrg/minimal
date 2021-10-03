package org.yastech.minimal.dispatcher

import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class SearchDispatcher(private val template: SimpMessagingTemplate)
{

    // Send Search
    fun send(session: String, ms: String)
    {
        val headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE)

        headerAccessor.sessionId = session

        headerAccessor.setLeaveMutable(true)

        template.convertAndSendToUser(session,"/search/get", ms, headerAccessor.messageHeaders)
    }
}