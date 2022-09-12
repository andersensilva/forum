package br.com.projetospring.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "andersen",
        email = "andersen.silva@gmail.com",
        password = "123456"
    )
}