package io.github.lsj8367.exception;

import io.github.lsj8367.controller.UserController;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "io.github.lsj8367", basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        return ResponseEntity.badRequest().body(Map.of(
            "timestamp", LocalDateTime.now(),
            "path", httpServletRequest.getRequestURI(),
            "status", 400,
            "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "message", Objects.requireNonNull(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
        ));
    }

}
