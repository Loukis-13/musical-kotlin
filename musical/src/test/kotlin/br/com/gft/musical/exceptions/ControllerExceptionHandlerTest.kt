package br.com.gft.musical.exceptions

import br.com.gft.musical.controllers.EstiloController
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class ControllerExceptionHandlerTest {
    private lateinit var mockMvc: MockMvc
    private val estiloController = mockk<EstiloController>()

    @BeforeEach
    fun setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(estiloController)
            .setControllerAdvice(ControllerExceptionHandler())
            .build()
    }

    @Test
    fun handleNoSuchElementException(){
        every { estiloController.findById(any()) } throws NoSuchElementException("Estilo não encontrado")

        mockMvc.perform(get("/estilo/1"))
            .andDo(print())
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.status").value("NOT_FOUND"))
            .andExpect(jsonPath("$.message").value("Entidade não encontrada"))
            .andExpect(jsonPath("$.errors").value("Estilo não encontrado"))
    }

    @AfterEach
    internal fun tearDown() {
        unmockkAll()
    }
}