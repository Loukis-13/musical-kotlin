package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Artista
import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.entities.Musica
import br.com.gft.musical.repositories.impl.MusicaRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class MusicaServiceImplTest {
    @MockK
    private lateinit var musicaRepository: MusicaRepository
    @InjectMockKs
    private lateinit var musicaService: MusicaServiceImpl

    private val musicaTeste = Musica(1, "Debil metal", Artista(), Estilo())

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun pegarTodosMusicas() {
        every { musicaRepository.findAll() } returns listOf(musicaTeste)

        val musicas: List<Musica> = musicaService.getAll().body!!

        assertNotNull(musicas)
        assertNotNull(musicas[0])
    }

    @Test
    fun pegarMusica() {
        every { musicaRepository.findById(any()) } returns musicaTeste

        val musica: Musica = musicaService.searchEntity(1).body!!

        assertNotNull(musica)
        assertEquals(musica.id, 1)
        assertEquals(musica.nome, "Debil metal")
        assertEquals(musica.artista!!.javaClass, Artista::class.java)
        assertEquals(musica.estilo!!.javaClass, Estilo::class.java)
    }

    @Test
    fun salvarMusica() {
        every { musicaRepository.save(musicaTeste) } returns musicaTeste

        val musica: Musica = musicaService.saveEntity(musicaTeste).body!!
        assertNotNull(musica)
    }

    @Test
    fun atualizarMusica() {
        every { musicaRepository.update(any(), ofType(Musica::class)) } returns
                Musica(1, "1406", null, null)

        val musica: Musica = musicaService.updateEntity(1, Musica(null, "1406", null, null)).body!!

        assertNotNull(musica)
        assertEquals(musica.id, 1)
        assertEquals(musica.nome, "1406")
    }

    @Test
    fun excluirMusica() {
        every { musicaRepository.deleteById(any()) } returns Unit
        musicaService.deleteEntity(1)
        io.mockk.verify(exactly = 1) { musicaRepository.deleteById(1) }
    }

    @Test
    fun musicaNaoExistente() {
        every { musicaRepository.findById(any()) } throws NoSuchElementException("No value present")
        try {
            musicaService.searchEntity(1)
        } catch (ex: NoSuchElementException) {
            assertEquals(ex.message, "No value present")
        }
        io.mockk.verify(exactly = 1) { musicaRepository.findById(1) }
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}