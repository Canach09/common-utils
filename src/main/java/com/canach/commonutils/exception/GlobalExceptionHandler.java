package com.canach.commonutils.exception;//package com.example.passwordmanager.exception;
//
//import com.example.passwordmanager.dto.response.MessageResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<MessageResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        log.error("Recurso no encontrado: {}", ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<MessageResponse> handleUnauthorizedException(UnauthorizedException ex) {
//        log.error("Acceso no autorizado: {}", ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<MessageResponse> handleBadCredentialsException(BadCredentialsException ex) {
//        log.error("Credenciales incorrectas: {}", ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse("Nombre de usuario o contraseña incorrectos"),
//                HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        log.error("Error de validación: {}", errors);
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MessageResponse> handleGlobalException(Exception ex, WebRequest request) {
//        log.error("Error no controlado: {}", ex.getMessage(), ex);
//        return new ResponseEntity<>(new MessageResponse("Ha ocurrido un error inesperado"),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}