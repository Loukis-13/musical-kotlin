package br.com.gft.musical.repositories.impl

import br.com.gft.musical.entities.Artista
import br.com.gft.musical.repositories.MusicalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class ArtistaRepository(@Autowired val entityManager: EntityManager): MusicalRepository<Artista> {
    @Transactional
    override fun save(entity: Artista) = entityManager.persist(entity).run { entity }

    @Transactional
    override fun update(id: Int, entity: Artista): Artista {
        findById(id)
        entity.id = id
        return entityManager.merge(entity)
    }

    override fun findAll(): List<Artista> =
        entityManager.createQuery("SELECT a FROM Artista a", Artista::class.java).resultList

    override fun findById(id: Int) =
        entityManager.find(Artista::class.java, id) ?: throw NoSuchElementException("Artista não encontrado")

        @Transactional
    override fun deleteById(id: Int) = entityManager.remove(findById(id))
}