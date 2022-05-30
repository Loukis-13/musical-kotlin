package br.com.gft.musicalkafka.services

import br.com.gft.musicalkafka.entities.Artista
import com.fasterxml.jackson.core.JsonProcessingException
import org.apache.kafka.common.KafkaException
import org.springframework.kafka.support.JacksonUtils
import org.springframework.stereotype.Service

@Service
class ArtistaService(val sendMessage: SendToKafka) {
    fun sendArtistaToKafka(artista: Artista) {
        try {
            sendMessage.sendMessage(JacksonUtils.enhancedObjectMapper().writeValueAsString(artista));
        } catch (e: JsonProcessingException) {
            e.printStackTrace();
        } catch (e: KafkaException) {
            e.printStackTrace();
        }
    }
}