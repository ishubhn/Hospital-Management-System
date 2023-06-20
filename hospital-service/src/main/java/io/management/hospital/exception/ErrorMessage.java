package io.management.hospital.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private LocalDateTime timestamp;
	private String message;
	private String status;
}
