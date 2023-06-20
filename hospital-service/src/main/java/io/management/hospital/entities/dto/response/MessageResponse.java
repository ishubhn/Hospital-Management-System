package io.management.hospital.entities.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageResponse extends Response {

	public MessageResponse(String message, String status) {
		super(message, status);
	}
}
