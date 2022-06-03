package br.com.gft.musical.repositories

import java.util.*

interface MusicalRepository<T> {
    fun save(entity: T): T
    fun update(id: Int, entity: T): T
    fun findAll(): List<T>
    fun findById(id: Int): T
    fun deleteById(id: Int)
}
