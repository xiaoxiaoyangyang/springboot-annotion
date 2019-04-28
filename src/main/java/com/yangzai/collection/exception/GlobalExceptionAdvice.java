package com.yangzai.collection.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * @author guozhiyang_vendor
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> errorHandler(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String code = bindingResult.getFieldError().getDefaultMessage();
        return new ResponseEntity<>(new ExceptionBody(
                request.getRequestURI(),
                code,
                request.getMethod()
        ), HttpStatus.BAD_REQUEST);
    }
}
