package br.com.gft.musical.services.impl

import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.repositories.impl.EstiloRepository
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


internal class EstiloServiceImplTest {
    @MockK
    private lateinit var estiloRepository: EstiloRepository
    @InjectMockKs
    private lateinit var estiloService: EstiloServiceImpl

    private val estiloTeste = Estilo(1, "Metal")

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun pegarTodosEstilos() {
        every { estiloRepository.findAll() } returns listOf(Estilo())

        val estilos: List<Estilo>? = estiloService.getAll().body

        assertNotNull(estilos)
        assertNotNull(estilos!![0])
    }

    @Test
    fun pegarEstilo() {
        every { estiloRepository.findById(1) } returns estiloTeste

        val estilo: Estilo = estiloService.searchEntity(1).body!!

        assertNotNull(estilo)
        assertEquals(estilo.id, 1)
        assertEquals(estilo.descricao, "Metal")
    }

    @Test
    fun salvarEstilo() {
        every { estiloRepository.save(estiloTeste) } returns estiloTeste

        val estilo: Estilo = estiloService.saveEntity(estiloTeste).body!!

        assertNotNull(estilo)
    }

    @Test
    fun atualizarEstilo() {
        every { estiloRepository.update(1, ofType(Estilo::class)) } returns Estilo(1, "Pagode")

        val estilo: Estilo = estiloService.updateEntity(1, Estilo(null, "Pagode")).body!!

        assertNotNull(estilo)
        assertEquals(estilo.id, 1)
        assertEquals(estilo.descricao, "Pagode")
    }

    @Test
    fun excluirEstilo() {
        every { estiloRepository.deleteById(1) } returns Unit
        estiloService.deleteEntity(1)
        io.mockk.verify(exactly = 1) { estiloRepository.deleteById(1) }
    }

    @Test
    fun estiloNaoExistente() {
        every { estiloRepository.findById(1) } throws NoSuchElementException("No value present")
                try {
            estiloService.searchEntity(1)
        } catch (ex: NoSuchElementException) {
            assertEquals(ex.message, "No value present")
        }
        io.mockk.verify(exactly = 1) { estiloRepository.findById(1) }
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}