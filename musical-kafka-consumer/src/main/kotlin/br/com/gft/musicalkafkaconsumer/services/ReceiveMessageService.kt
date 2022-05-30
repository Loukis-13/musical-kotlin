package br.com.gft.musicalkafkaconsumer.services

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ReceiveMessageService {
    @KafkaListener(topics = ["\${spring.kafka.topic.consumer}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(message: String) {
        println(message)
    }
}