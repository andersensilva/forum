package br.com.projetospring.forum.repository

import br.com.projetospring.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long>{
}