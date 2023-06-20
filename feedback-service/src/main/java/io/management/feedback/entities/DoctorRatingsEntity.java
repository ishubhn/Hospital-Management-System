package io.management.feedback.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DoctorRatings")
public class DoctorRatingsEntity {
	@Id
	private String ratingId;
	private String doctorId;
	private String userId;  // one who gave the rating
	@Min(value = 0)
	@Max(value = 5)
	private int ratings;
	@Size(max = 300)
	private String feedback;

	public DoctorRatingsEntity(String doctorId, String userId, int ratings, String feedback) {
		this.doctorId = doctorId;
		this.userId = userId;
		this.ratings = ratings;
		this.feedback = feedback;
	}
}
