package br.com.projetospring.forum.exception

import br.com.projetospring.forum.DTO.ErrorView
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import br.com.projetospring.forum.exception.ExceptionHandler as ExceptionExceptionHandler

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError( exception: Exception,
                        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}