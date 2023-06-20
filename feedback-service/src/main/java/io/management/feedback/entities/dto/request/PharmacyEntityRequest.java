package io.management.feedback.entities.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyEntityRequest {
	private String medicineId;
	private String userId;  // one who gave the rating
	private int ratings;
	private String feedback;
}
