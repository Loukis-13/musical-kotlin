package br.com.gft.musical.exceptions

import org.springframework.http.HttpStatus

class Error(
    val message: String,
    val errors: String,
    val status: HttpStatus,
)