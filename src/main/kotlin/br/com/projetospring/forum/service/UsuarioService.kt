package br.com.projetospring.forum.service

import br.com.projetospring.forum.model.Usuario
import br.com.projetospring.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UsuarioService(
    private val repository: UsuarioRepository) :
    UserDetailsService {

    fun buscaPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }


}
