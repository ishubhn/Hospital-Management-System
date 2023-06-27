package io.service.auth.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorInfo {
    private LocalDateTime timestamp;
    private String errorCode;
    private String description;

    public ErrorInfo(String errorCode, String description) {
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.description = description;
    }
}
