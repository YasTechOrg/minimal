package org.yastech.minimal.handlers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UrlHandler
{
    @RequestMapping(value = ["/{path:[^.]*}"])
    fun surface(@PathVariable path: String): String
    {
        return "forward:/"
    }
}