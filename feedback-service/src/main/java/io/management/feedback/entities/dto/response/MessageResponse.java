package io.management.feedback.entities.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
	private LocalDateTime timestamp;
	private String message;
	private String status;

	public MessageResponse(String message, String status) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.status = status;
	}
}
