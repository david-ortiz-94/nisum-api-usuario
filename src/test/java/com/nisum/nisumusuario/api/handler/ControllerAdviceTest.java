package com.nisum.nisumusuario.api.handler;

import com.nisum.nisumusuario.utils.exceptions.BadRequestException;
import com.nisum.nisumusuario.utils.exceptions.ConflictException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerAdviceTest {
    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void handleBadRequestExceptionTest() {
        BadRequestException throwable = new BadRequestException("abc");
        controllerAdvice.handleBadRequestException(throwable);
    }

    @Test
    void handleMethodArgumentNotValidExceptionTest() {
        Method method = new Object() {
        }.getClass().getEnclosingMethod();
        MethodParameter parameter = Mockito.mock(MethodParameter.class);
        Mockito.when(parameter.getMethod()).thenReturn(method);
        Mockito.when(parameter.getExecutable()).thenReturn(method);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasFieldErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(
                Collections.singletonList(new FieldError("fieldName", "fieldName", "campo")));

        Mockito.when(bindingResult.getAllErrors()).thenReturn(Lists.newArrayList());
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter,
                bindingResult);
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("");
        controllerAdvice.handleMethodArgumentNotValidException(exception);
    }

    @Test
    void handleConflictExceptionTest() {
        ConflictException throwable = mock(ConflictException.class);
        controllerAdvice.handleConflictException(throwable);
    }

    @Test
    void handleExceptionTest() {
        Exception throwable = mock(Exception.class);
        controllerAdvice.handleException(throwable);
    }
}
