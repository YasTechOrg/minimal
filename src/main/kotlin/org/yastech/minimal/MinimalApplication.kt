package org.yastech.minimal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MinimalApplication

fun main(args: Array<String>)
{
    runApplication<MinimalApplication>(*args)
}
