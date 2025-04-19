package com.web.policy.model;

import java.time.LocalDateTime;
import java.util.List;

public class ApiResponse<T> {
    private String messageId;
    private LocalDateTime requestDateTime;
    private LocalDateTime responseDateTime;
    private Status status;
    private T data;

    public static class Status {
        private int code;
        private String message;

        public Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        // Getters & Setters
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    // Getters & Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public LocalDateTime getRequestDateTime() { return requestDateTime; }
    public void setRequestDateTime(LocalDateTime requestDateTime) { this.requestDateTime = requestDateTime; }

    public LocalDateTime getResponseDateTime() { return responseDateTime; }
    public void setResponseDateTime(LocalDateTime responseDateTime) { this.responseDateTime = responseDateTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}

