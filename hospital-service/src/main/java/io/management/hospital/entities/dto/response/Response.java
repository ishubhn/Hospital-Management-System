package io.management.hospital.entities.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {
	private LocalDateTime timestamp;
	private String message;
	private String status;

	public Response(String message, String status) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.status = status;
	}
}
