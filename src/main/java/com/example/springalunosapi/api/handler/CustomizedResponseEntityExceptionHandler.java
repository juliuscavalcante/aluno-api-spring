package com.example.springalunosapi.api.handler;

import com.example.springalunosapi.api.exception.ExceptionResponse;
import com.example.springalunosapi.api.exception.IdInvalidoException;
import com.example.springalunosapi.api.exception.RecursoNaoEncontradoException;
import com.example.springalunosapi.api.exception.ValidacaoException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    private static final Log log = LogFactory.getLog(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler({ RecursoNaoEncontradoException.class })
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception, WebRequest webRequest) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final ExceptionResponse exceptionResponse = tratar(exception, webRequest, httpStatus);
        return ResponseEntity.status(httpStatus).body(exceptionResponse);
    }


    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public final ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(Exception exception,
                                                                                        WebRequest webRequest) {
        final HttpStatus httpStatus = HttpStatus.GONE;
        final ExceptionResponse exceptionResponse = tratar(exception, webRequest, httpStatus);
        return ResponseEntity.status(httpStatus).body(exceptionResponse);
    }


    @ExceptionHandler({ IdInvalidoException.class })
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception exception,
                                                                              WebRequest webRequest) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ExceptionResponse exceptionResponse = tratar(exception, webRequest, httpStatus);
        return ResponseEntity.badRequest().body(exceptionResponse);
    }


    @ExceptionHandler(ValidacaoException.class)
    public final ResponseEntity<ExceptionResponse> handleValidacaoException(ValidacaoException exception,
                                                                            WebRequest webRequest) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ExceptionResponse exceptionResponse = tratar(exception, webRequest, httpStatus, exception.getErros());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }


    private ExceptionResponse tratar(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        List<String> erros = new LinkedList<>();
        erros.add(exception.getMessage());
        return tratar(exception, webRequest, httpStatus, erros);
    }


    private ExceptionResponse tratar(Exception exception, WebRequest webRequest, HttpStatus httpStatus,
                                     List<String> erros) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now() , erros,
                ExceptionUtils.getRootCauseMessage(exception), webRequest.getDescription(Boolean.FALSE), httpStatus);
        log.error(String.format("Erro durante requisição - |%s| ## Detalhe do Erro: |%s|", erros.toString(),
                exceptionResponse.getMessageDevelop()));
        return exceptionResponse;
    }
}