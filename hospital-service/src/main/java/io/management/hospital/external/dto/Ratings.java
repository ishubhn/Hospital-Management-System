package io.management.hospital.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
	private String ratingId;
	private String doctorId;
	private String userId;
	private int ratings;
	private String feedback;
}
