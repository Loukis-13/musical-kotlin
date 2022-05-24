package br.com.gft.musical.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "MUSICA")
data class Musica(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "NOME")
    var nome: String? = null,

    @ManyToOne
    @JsonIgnoreProperties("musicas")
    var artista: Artista? = null,

    @ManyToOne
    var estilo: Estilo? = null,
)
