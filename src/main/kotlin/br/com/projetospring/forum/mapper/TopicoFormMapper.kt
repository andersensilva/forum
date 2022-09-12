package br.com.projetospring.forum.mapper

import br.com.projetospring.forum.DTO.TopicoForm
import br.com.projetospring.forum.model.Topico
import br.com.projetospring.forum.service.CursoService
import br.com.projetospring.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
): Mapper<TopicoForm, Topico>{
    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorID(t.idCurso),
            autor = usuarioService.buscaPorId(t.idAutor)

        )
    }

}
