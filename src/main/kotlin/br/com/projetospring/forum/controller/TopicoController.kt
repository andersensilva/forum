package br.com.projetospring.forum.controller

import br.com.projetospring.forum.DTO.TopicoView
import br.com.projetospring.forum.DTO.TopicoForm
import br.com.projetospring.forum.DTO.AtualizacaoTopicoForm
import br.com.projetospring.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@Suppress("UNREACHABLE_CODE")
@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"]) paginacao: Pageable
    ): Page<TopicoView> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscaId(@PathVariable id: Long):TopicoView {
        return service.buscaaId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(form)
        val uri = uriBuilder.path("/topico/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView>{
        val topicoView = service.atualizar(form)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = ["topicos"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long){
        service.deletar(id)

    }
}