package br.com.gft.musicalkafka.services

import org.apache.kafka.common.KafkaException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class SendToKafka(
    @Value("\${original.alcd.kafka.topic}") val topicName: String,
    @Autowired val kafkaTemplate: KafkaTemplate<String, String>,
) {
    fun sendMessage(msg: String) {
        kafkaTemplate.send(topicName, msg).completable().whenComplete { _, ex ->
            if (ex != null) throw KafkaException(ex.message)
        }
    }
}