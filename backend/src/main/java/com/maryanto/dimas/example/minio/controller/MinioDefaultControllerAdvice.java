package com.maryanto.dimas.example.minio.controller;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MinioDefaultControllerAdvice {

    @ExceptionHandler({ConnectException.class})
    public ResponseEntity<?> handleMinioNotConnect(ConnectException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity
                .internalServerError()
                .body(body);
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<?> handleStorageCantWrite(IOException ex, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity
                .internalServerError()
                .body(body);
    }

    @ExceptionHandler({IOFileUploadException.class})
    public ResponseEntity<?> handleFileNotFound(IOFileUploadException ex, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity
                .internalServerError()
                .body(body);
    }
}
