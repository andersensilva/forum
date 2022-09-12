package br.com.projetospring.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Agendamento kotlin basico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}