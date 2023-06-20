package io.management.feedback.entities.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyRatingsResponse {
	private String ratingsId;
	private String medicineId;
	private String userId;
	private int ratings;
	private String feedback;
}
