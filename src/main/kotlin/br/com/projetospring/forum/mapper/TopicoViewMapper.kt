package br.com.projetospring.forum.mapper

import br.com.projetospring.forum.DTO.TopicoView
import br.com.projetospring.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView(
            id= t.id,
            titulo= t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status,
            dataAlteracao = t.dataAlteracao
        )
    }
}