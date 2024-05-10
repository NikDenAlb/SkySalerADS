package selfConstructed.SkySalerADS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Пользователь не найден");
    }
    @ExceptionHandler(UserAlreadyHereException.class)
    public ResponseEntity<String> userAlreadyHereException(UserAlreadyHereException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Пользователь найден");
    }
}
