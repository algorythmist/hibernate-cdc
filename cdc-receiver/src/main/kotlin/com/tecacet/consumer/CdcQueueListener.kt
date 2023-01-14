package com.tecacet.consumer

import com.tecacet.cdc.model.CdcEvent
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class CdcQueueListener {

    @RabbitListener(queues = arrayOf("cdc-queue"))
    fun messageReceiver(event : CdcEvent) {
        println("received event: $event")
    }

}
