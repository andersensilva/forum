package br.com.projetospring.forum.DTO

import javax.validation.constraints.NotNull

class AtualizacaoTopicoForm(
    @field:NotNull val id: Long,
    @field:NotNull val titulo: String,
    @field:NotNull val mensagem: String
)
