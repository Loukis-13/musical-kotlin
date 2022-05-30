package br.com.gft.musicalkafkaconsumer.configs

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class TopicConfig(
    @Value("\${spring.kafka.bootstrap-servers}") val bootstrapAddressMessage: String
) {
    @Bean
    fun kafkaAdminMessage() = KafkaAdmin(mapOf(
        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddressMessage
    ))
}