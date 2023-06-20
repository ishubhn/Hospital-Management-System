package io.management.pharmacy.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private LocalDateTime dateTime;
	private String message;
	private String details;

	public ErrorResponse(String message, String details) {
		this.dateTime = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}
}
