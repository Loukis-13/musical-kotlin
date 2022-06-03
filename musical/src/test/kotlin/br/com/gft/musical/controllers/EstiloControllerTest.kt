package br.com.gft.musical.controllers

import br.com.gft.musical.entities.Estilo
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
internal class EstiloControllerTest {
    private val estiloService = mockk<MusicalService<Estilo>>()
    private lateinit var mockMvc: MockMvc
    private val mapper = ObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(EstiloController(estiloService)).build()
    }

    @Test
    fun findAll() {
        every { estiloService.getAll() } returns
                ResponseEntity.ok(listOf(
                    Estilo(1, "Estilo 1"),
                    Estilo(2, "Estilo 2"),
                ))

        mockMvc.perform(MockMvcRequestBuilders.get("/estilo"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").isNumber)
            .andExpect(jsonPath("$[0].descricao").isString)
    }

    @Test
    fun saveEstilo() {
        every { estiloService.saveEntity(ofType(Estilo::class)) } returns
                ResponseEntity.status(HttpStatus.CREATED).body(Estilo(1, "Estilo"))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/estilo")
                .content(mapper.writeValueAsString(Estilo(null, "Estilo")))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.descricao").isString)
    }

    @Test
    fun findById() {
        every { estiloService.searchEntity(any()) } returns
                ResponseEntity.ok(Estilo(1, "Estilo"))

        mockMvc.perform(MockMvcRequestBuilders.get("/estilo/1"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.descricao").isString)
    }

    @Test
    fun updateEstilo() {
        every { estiloService.updateEntity(any(), ofType(Estilo::class)) } returns
                ResponseEntity.ok(Estilo(1, "Slayer"))

        mockMvc.perform(
            MockMvcRequestBuilders.put("/estilo/1")
                .content(mapper.writeValueAsString(Estilo(null, "Slayer")))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.descricao").value("Slayer"))
    }

    @Test
    fun deleteEstilo() {
        every { estiloService.deleteEntity(ofType(Int::class)) } returns ResponseEntity.noContent().build()

        mockMvc.perform(MockMvcRequestBuilders.delete("/estilo/{id}", 1))
            .andDo(print())
            .andExpect(status().isNoContent)
            .andExpect(jsonPath("$").doesNotExist())
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}