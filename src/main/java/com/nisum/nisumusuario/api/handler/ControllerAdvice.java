package com.nisum.nisumusuario.api.handler;

import com.nisum.nisumusuario.api.model.Error;
import com.nisum.nisumusuario.utils.constants.Constants;
import com.nisum.nisumusuario.utils.exceptions.BadRequestException;
import com.nisum.nisumusuario.utils.exceptions.ConflictException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler({BadRequestException.class,
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            BindException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final Error handleBadRequestException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Error.builder()
                .mensaje(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.add(String.format(Constants.FORMAT_STRING_MESSAGE, field, message));
        });

        log.error(ex.getMessage(), ex);
        return Error.builder()
                .mensaje(errors.stream()
                        .collect(Collectors.joining(Constants.SPACE_STRING)))
                .build();
    }
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final Error handleConflictException(ConflictException ex) {
        log.error(ex.getMessage(), ex);
        return Error.builder()
                .mensaje(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Error handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Error.builder()
                .mensaje(ex.getMessage())
                .build();
    }
}
