package com.loansharkmss.LoanShark.v1.exceptions;

import com.loansharkmss.LoanShark.v1.config.ImageConfig;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "404 Not Found");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseParameters);
    }

    @ExceptionHandler({InternalServerError.class})
    public ResponseEntity<Map<String, String>> handleInternalServerError(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "500 Internal Server Error");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseParameters);
    }

    @ExceptionHandler({BadRequest.class})
    public ResponseEntity<Map<String, String>> handleBadRequest(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "400 Bad Request");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.badRequest().body(responseParameters);
    }

    @ExceptionHandler({Unauthorized.class})
    public ResponseEntity<Map<String, String>> handleUnauthorized(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "401 Unauthorized");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseParameters);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "400 Bad Request");
        responseParameters.put("Reason: ", message);

        return ResponseEntity.badRequest().body(responseParameters);
    }

    @ExceptionHandler({UnsupportedMediaType.class})
    public ResponseEntity<Map<String, String>> handleUnsupportedMediaType(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "413 Request Entity Too Large");
        responseParameters.put("Reason: ", exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(responseParameters);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request, HttpServletResponse response){

        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Status: ", "415 Unsupported Media Type");
        responseParameters.put("Reason: ", "Maximum upload size exceeded! The field image exceeds its maximum permitted size of " + ImageConfig.MAX_IMAGE_SIZE_IN_BYTES + " bytes");

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(responseParameters);
    }
}
