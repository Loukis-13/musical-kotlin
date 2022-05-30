package br.com.gft.musical.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "ARTISTA")
class Artista(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "NOME")
    var nome: String? = null,

    @OneToMany(mappedBy = "artista")
    @JsonIgnoreProperties("artista")
    var musicas: List<Musica>? = null,
)