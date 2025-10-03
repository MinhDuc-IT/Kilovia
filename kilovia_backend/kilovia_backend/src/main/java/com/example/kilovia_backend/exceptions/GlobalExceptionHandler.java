package com.example.kilovia_backend.exceptions;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import com.example.kilovia_backend.exceptions.exceptions.*;
import com.example.kilovia_backend.dtos.responseDtos.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred", ex);
        return buildErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (User Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(SubjectGradeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleSubjectGradeNotFound(SubjectGradeNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Subject Grade Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(QuestionNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Question Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(QuestionTypeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(QuestionTypeNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Question Type Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(SubTopicNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(SubTopicNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (SubTopic Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(UpdateFailedException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Update Failed): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(DeleteFailedException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Delete Failed): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(GradeNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Grade Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(TopicNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Topic Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleQuestionsNotFound(SubjectNotFoundException ex, HttpServletRequest request) {
        logger.error(() -> "Unexpected error occurred (Subject Not Found): ", ex);
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(String message, HttpStatus status, String path) {
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(error);
    }
}
