package com.example.demo.web.advice;

import com.example.demo.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.sql.SQLException;

@ControllerAdvice
public class AdviceHandler {
    @ExceptionHandler({RecordNotFoundException.class})
    public ProblemDetail handleRecordNotFound(RecordNotFoundException e) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problem.setTitle("not found");
        problem.setType(URI.create("/api/v1/error/not-found"));
        return problem;
    }

    @ExceptionHandler({SQLException.class})
    public ProblemDetail handleRecordNotFound(SQLException e) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("illegal state");
        problem.setType(URI.create("/api/v1/error/player/limit"));
        return problem;
    }
}
