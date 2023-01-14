package com.tecacet.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReceiverApp

fun main(args: Array<String>) {
    runApplication<ReceiverApp>(*args)
}

