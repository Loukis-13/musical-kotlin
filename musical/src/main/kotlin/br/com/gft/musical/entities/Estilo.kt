package br.com.gft.musical.entities

import javax.persistence.*

@Entity
@Table(name = "ESTILO")
data class Estilo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "DESCRICAO")
    var descricao: String? = null,
)
