package br.com.projetospring.forum.model

import br.com.projetospring.forum.DTO.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() =  TopicoView(
        id = 1,
        titulo = "kotlin avançado",
        mensagem = "aprendendo curso avançado",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()

    )
}