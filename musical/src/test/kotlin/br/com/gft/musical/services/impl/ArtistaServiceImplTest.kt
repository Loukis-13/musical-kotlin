package br.com.gft.musical.services.impl

import br.com.gft.musical.configs.MusicalFeignClient
import br.com.gft.musical.entities.Artista
import br.com.gft.musical.entities.Musica
import br.com.gft.musical.repositories.impl.ArtistaRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import java.util.*

internal class ArtistaServiceImplTest {
    @MockK private lateinit var artistaRepository: ArtistaRepository
    @MockK private lateinit var musicalFeignClient: MusicalFeignClient
    @InjectMockKs private lateinit var artistaService: ArtistaServiceImpl

    private val artistaTeste = Artista(1, "Raul", listOf(Musica()))

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getAll() {
        every { artistaRepository.findAll() } returns listOf(artistaTeste)
        val artistas: List<Artista>? = artistaService.getAll().body

        assertNotNull(artistas)
        assertNotNull(artistas!![0])
    }

    @Test
    fun saveEntity() {
        every { artistaRepository.save(artistaTeste) } returns  artistaTeste

        val artista: Artista? = artistaService.saveEntity(artistaTeste).body

        assertNotNull(artista)
    }

    @Test
    fun searchEntity() {
        every { artistaRepository.findById(any()) } returns artistaTeste
        every { musicalFeignClient.returnArtista(artistaTeste) } returns ResponseEntity.of(Optional.of(artistaTeste))

        val artista: Artista = artistaService.searchEntity(1).body!!

        assertNotNull(artista)
        assertEquals(artista.id, 1)
        assertEquals(artista.nome, "Raul")
        assertEquals(artista.musicas!![0]::class.java, Musica::class.java)
    }

    @Test
    fun updateEntity() {
        every { artistaRepository.update(any(), ofType(Artista::class)) } returns Artista(1, "Zeca pagodinho", null)

        val artista: Artista? = artistaService.updateEntity(1, Artista(null, "Zeca pagodinho", null)).body

        assertNotNull(artista)
        assertEquals(artista!!.id, 1)
        assertEquals(artista.nome, "Zeca pagodinho")
    }

    @Test
    fun deleteEntity() {
        every { artistaRepository.deleteById(any()) } returns Unit
        artistaService.deleteEntity(1)
        verify(exactly = 1) { artistaRepository.deleteById(1) }
    }

    @Test
    fun artistaNaoExistente() {
        every { artistaRepository.findById(any()) } throws NoSuchElementException("No value present")
        try {
            artistaService.searchEntity(1)
        } catch (ex: NoSuchElementException) {
            assertEquals(ex.message, "No value present")
        }
        verify(exactly = 1) { artistaRepository.findById(1) }
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}