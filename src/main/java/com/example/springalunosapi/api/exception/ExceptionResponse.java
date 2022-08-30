package com.example.springalunosapi.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate date;

    private List<String> messages;

    private String messageDevelop;

    private String details;

    @JsonIgnore
    private HttpStatus httpStatus;


    public ExceptionResponse(LocalDate date, List<String> messages, String messageDevelop, String details, HttpStatus httpStatus) {
        this.date = date;
        this.messages = messages;
        this.messageDevelop = messageDevelop;
        this.details = details;
        this.httpStatus = httpStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getMessageDevelop() {
        return messageDevelop;
    }

    public void setMessageDevelop(String messageDevelop) {
        this.messageDevelop = messageDevelop;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
