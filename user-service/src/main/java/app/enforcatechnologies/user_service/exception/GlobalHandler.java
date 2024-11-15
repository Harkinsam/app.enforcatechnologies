package app.enforcatechnologies.user_service.exception;

import app.enforcatechnologies.user_service.response.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Response> handleConflictException(ConflictException ex) {
        Response response = new Response();
        response.setResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(JobTalentNotFoundException.class)
    public ResponseEntity<String> handleJobTalentNotFound(JobTalentNotFoundException ex) {
        // Return custom error message and status 404 (Not Found)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException ex) {
        Response errorResponse = new Response(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
