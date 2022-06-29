package br.com.projetospring.forum.repository

import br.com.projetospring.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

    fun findByEmail(username : String?): Usuario?
}