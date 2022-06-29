package br.com.projetospring.forum.DTO

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class TopicoForm (
    @field:NotEmpty val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)
