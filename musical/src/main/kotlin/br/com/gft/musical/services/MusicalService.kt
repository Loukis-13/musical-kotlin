package br.com.gft.musical.services

interface MusicalService<T> {
    fun getAll(): List<T>
    fun saveEntity(entity: T): T
    fun searchEntity(id: Int): T
    fun updateEntity(id: Int, entity: T): T
    fun deleteEntity(id: Int)
}