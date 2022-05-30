package br.com.gft.respostamusical.services

import br.com.gft.respostamusical.configs.ArtistaFeignClient
import br.com.gft.respostamusical.models.Artista
import org.springframework.stereotype.Service

@Service
class ArtistaService(val artistaFeignClient: ArtistaFeignClient) {
    fun getArtista(artistaId: Int): Artista {
        return artistaFeignClient.findById(artistaId).body!!
    }
}