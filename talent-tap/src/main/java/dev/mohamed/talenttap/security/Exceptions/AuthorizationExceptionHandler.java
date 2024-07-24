package dev.mohamed.talenttap.security.authorizaiton;

import dev.mohamed.talenttap.core.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static dev.mohamed.talenttap.security.authorizaiton.AuthorizationErrorCodes.ACCOUNT_LOCKED;
import static dev.mohamed.talenttap.security.authorizaiton.AuthorizationErrorCodes.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class AuthorizationExceptionHandler {

  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {
    return ResponseEntity
        .status(UNAUTHORIZED)
        .body(
            ExceptionResponse.builder()
                .businessErrorCode(ACCOUNT_LOCKED.getCode())
                .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                .error(exp.getMessage())
                .build()
        );
  }
  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ExceptionResponse> handleException(DisabledException exp) {
    return ResponseEntity
        .status(UNAUTHORIZED)
        .body(
            ExceptionResponse.builder()
                .businessErrorCode(ACCOUNT_DISABLED.getCode())
                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                .error(exp.getMessage())
                .build()
        );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionResponse> handleException() {
    return ResponseEntity
        .status(UNAUTHORIZED)
        .body(
            ExceptionResponse.builder()
                .businessErrorCode(BAD_CREDENTIALS.getCode())
                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                .error("Login and / or Password is incorrect")
                .build()
        );
  }

  @ExceptionHandler(OperationNotPermittedException.class)
  public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp) {
    return ResponseEntity
        .status(BAD_REQUEST)
        .body(
            ExceptionResponse.builder()
                .error(exp.getMessage())
                .build()
        );
  }


}
