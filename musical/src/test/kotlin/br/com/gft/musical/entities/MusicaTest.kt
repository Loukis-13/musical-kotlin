package br.com.gft.musical.entities

import org.junit.jupiter.api.Test

internal class MusicaTest {
    @Test
    internal fun testarEntidades() {
        val artista = Artista()
        val estilo = Estilo()
        val musica = Musica()

        artista.id = 1
        artista.nome = "Raul"
        artista.musicas = listOf(Musica())

        estilo.id = 1
        estilo.descricao = "Blasting Deathmetal"

        musica.id = 1
        musica.nome = "Raul"
        musica.artista = artista
        musica.estilo = estilo

        println("Artista: ${artista.id} ${artista.nome} ${artista.musicas}")
        println("Estilo: ${estilo.id} ${estilo.descricao}")
        println("Musica: ${musica.id} ${musica.nome}")
    }
}