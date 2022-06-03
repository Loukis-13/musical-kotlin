package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Artista
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
internal class ArtistaControllerTest {
    private val artistaService = mockk<MusicalService<Artista>>()
    private lateinit var mockMvc: MockMvc
    private val mapper = ObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ArtistaController(artistaService)).build()
    }

    @Test
    fun findAll() {
        every { artistaService.getAll() } returns
                ResponseEntity.ok(listOf(
                    Artista(1, "Artista 1", listOf(Musica())),
                    Artista(2, "Artista 2", listOf(Musica())),
                ))

        mockMvc.perform(get("/artista"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").isNumber)
            .andExpect(jsonPath("$[0].nome").isString)
            .andExpect(jsonPath("$[0].musicas").isArray)
    }

    @Test
    fun saveArtista() {
        every { artistaService.saveEntity(ofType(Artista::class)) } returns
                ResponseEntity.status(HttpStatus.CREATED).body(Artista(1, "Artista", listOf(Musica())))

        mockMvc.perform(
            post("/artista")
                .content(mapper.writeValueAsString(Artista(null, "Artista", listOf(Musica()))))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.nome").value("Artista"))
            .andExpect(jsonPath("$.musicas").isArray)
    }

    @Test
    fun findById() {
        every { artistaService.searchEntity(any()) } returns
                ResponseEntity.ok(Artista(1, "Artista", listOf(Musica())))

        mockMvc.perform(get("/artista/1"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Artista"))
            .andExpect(jsonPath("$.musicas").isArray)
    }

    @Test
    fun updateArtista() {
        every { artistaService.updateEntity(any(), ofType(Artista::class)) } returns
                ResponseEntity.ok(Artista(1, "Slayer", listOf(Musica())))

        mockMvc.perform(
            put("/artista/1")
                .content(mapper.writeValueAsString(Artista(null, "Slayer", listOf(Musica()))))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Slayer"))
            .andExpect(jsonPath("$.musicas").isArray)
    }

    @Test
    fun deleteArtista() {
        every { artistaService.deleteEntity(ofType(Int::class)) } returns ResponseEntity.noContent().build()

        mockMvc.perform(delete("/artista/{id}", 1))
            .andDo(print())
            .andExpect(status().isNoContent)
            .andExpect(jsonPath("$").doesNotExist())
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}