package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Artista
import br.com.gft.musical.entities.Estilo
import br.com.gft.musical.entities.Musica
import br.com.gft.musical.services.MusicalService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
internal class MusicaControllerTest {
    private val musicaService = mockk<MusicalService<Musica>>()
    private lateinit var mockMvc: MockMvc
    private val mapper = ObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(MusicaController(musicaService)).build()
    }

    @Test
    fun findAll() {
        every { musicaService.getAll() } returns
                ResponseEntity.ok(listOf(
                    Musica(1, "Musica 1", Artista(), Estilo()),
                    Musica(2, "Musica 2", Artista(), Estilo()),
                ))

        mockMvc.perform(MockMvcRequestBuilders.get("/musica"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").isNumber)
            .andExpect(jsonPath("$[0].nome").isString)
            .andExpect(jsonPath("$[0].artista").exists())
            .andExpect(jsonPath("$[0].estilo").exists())
    }

    @Test
    fun saveMusica() {
        every { musicaService.saveEntity(ofType(Musica::class)) } returns
                ResponseEntity.status(HttpStatus.CREATED).body(Musica(1, "Musica", Artista(), Estilo()))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/musica")
                .content(mapper.writeValueAsString(Musica(null, "Musica", Artista(), Estilo())))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.nome").value("Musica"))
            .andExpect(jsonPath("$.artista").exists())
            .andExpect(jsonPath("$.estilo").exists())
    }

    @Test
    fun findById() {
        every { musicaService.searchEntity(any()) } returns
                ResponseEntity.ok(Musica(1, "Musica", Artista(), Estilo()))

        mockMvc.perform(MockMvcRequestBuilders.get("/musica/1"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Musica"))
            .andExpect(jsonPath("$.artista").exists())
            .andExpect(jsonPath("$.estilo").exists())
    }

    @Test
    fun updateMusica() {
        every { musicaService.updateEntity(any(), ofType(Musica::class)) } returns
                ResponseEntity.ok(Musica(1, "Reign in Blood", Artista(), Estilo()))

        mockMvc.perform(
            MockMvcRequestBuilders.put("/musica/1")
                .content(mapper.writeValueAsString(Musica(null, "Reign in Blood", Artista(), Estilo())))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Reign in Blood"))
            .andExpect(jsonPath("$.artista").exists())
            .andExpect(jsonPath("$.estilo").exists())
    }

    @Test
    fun deleteMusica() {
        every { musicaService.deleteEntity(ofType(Int::class)) } returns ResponseEntity.noContent().build()

        mockMvc.perform(MockMvcRequestBuilders.delete("/musica/{id}", 1))
            .andDo(print())
            .andExpect(status().isNoContent)
            .andExpect(jsonPath("$").doesNotExist())
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}