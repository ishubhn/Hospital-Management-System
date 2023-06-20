package io.management.feedback.entities.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRatingRequest {
	int ratings;
	String feedback;
	String ratingId;
	String userId;
}
