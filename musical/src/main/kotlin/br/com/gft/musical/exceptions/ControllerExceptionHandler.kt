package br.com.gft.musical.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler( NotFoundException::class )
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest) = ResponseEntity(
        Error("Entidade não encontrada", ex.message!!, HttpStatus.NOT_FOUND),
        HttpHeaders(),
        HttpStatus.NOT_FOUND
    )

    @ExceptionHandler( NoSuchElementException::class )
    fun handleNoSuchElementException(ex: NoSuchElementException, request: WebRequest) = ResponseEntity(
        Error("Entidade não encontrada", ex.message!!, HttpStatus.NOT_FOUND),
        HttpHeaders(),
        HttpStatus.NOT_FOUND
    )
}