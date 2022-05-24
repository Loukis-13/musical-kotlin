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
    override fun save(entity: Artista): Artista {
        entityManager.persist(entity)
        return entity
    }

    @Transactional
    override fun update(id: Int, entity: Artista): Artista {
        findById(id).orElseThrow()
        entity.id = id
        return entityManager.merge(entity)
    }

    override fun findAll(): List<Artista> {
        return entityManager.createQuery("SELECT a FROM Artista a", Artista::class.java).resultList
    }

    override fun findById(id: Int): Optional<Artista> {
        return Optional.ofNullable(entityManager.find(Artista::class.java, id))
    }

    @Transactional
    override fun deleteById(id: Int) {
        entityManager.remove(findById(id).orElseThrow())
    }
}