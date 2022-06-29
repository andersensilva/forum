package br.com.projetospring.forum.service

import br.com.projetospring.forum.model.Curso
import br.com.projetospring.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorID(id: Long):Curso {
        return repository.getOne(id)
    }

}
