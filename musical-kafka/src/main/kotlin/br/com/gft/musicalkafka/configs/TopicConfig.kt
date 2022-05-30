package br.com.gft.musicalkafka.configs

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class TopicConfig(
    @Value("spring.kafka.bootstrap-servers") val bootstrapAddress: String,
    @Value("original.alcd.kafka.topic") val topicName: String,
) {
    @Bean
    fun kafkaAdmin(): KafkaAdmin =
        KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress))
}