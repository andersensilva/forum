package br.com.projetospring.forum.service

import br.com.projetospring.forum.DTO.AtualizacaoTopicoForm
import br.com.projetospring.forum.DTO.TopicoForm
import br.com.projetospring.forum.DTO.TopicoView
import br.com.projetospring.forum.exception.NotFoundException
import br.com.projetospring.forum.mapper.TopicoFormMapper
import br.com.projetospring.forum.mapper.TopicoViewMapper
import br.com.projetospring.forum.model.Topico
import br.com.projetospring.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService( private val repository: TopicoRepository,
                     private val topicoViewMapper: TopicoViewMapper,
                     private val topicoFormMapper: TopicoFormMapper,
                     private val notFoundMessage: String = "Topico não encontrado"
) {

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
       val topicos = if(nomeCurso == null){
           repository.findAll(paginacao)
       }else {
           repository.findByCursoNome(nomeCurso, paginacao)
       }
        return topicos.map{ t->
            topicoViewMapper.map(t)
        }
    }

    fun buscaaId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
       repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
       repository.deleteById(id)
    }
}