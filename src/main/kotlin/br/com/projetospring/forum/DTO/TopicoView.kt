package br.com.projetospring.forum.DTO

import br.com.projetospring.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
    )
