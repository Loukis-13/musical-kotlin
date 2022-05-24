package br.com.gft.musical.repositories.impl

import br.com.gft.musical.entities.Musica
import br.com.gft.musical.repositories.MusicalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class MusicaRepository(@Autowired val entityManager: EntityManager): MusicalRepository<Musica> {
    @Transactional
    override fun save(entity: Musica): Musica {
        entityManager.persist(entity)
        return entity
    }

    @Transactional
    override fun update(id: Int, entity: Musica): Musica {
        findById(id).orElseThrow()
        entity.id = id
        return entityManager.merge(entity)
    }

    override fun findAll(): List<Musica> {
        return entityManager.createQuery("SELECT m FROM Musica m", Musica::class.java).resultList
    }

    override fun findById(id: Int): Optional<Musica> {
        return Optional.ofNullable(entityManager.find(Musica::class.java, id))
    }

    @Transactional
    override fun deleteById(id: Int) {
        entityManager.remove(findById(id).orElseThrow())
    }
}