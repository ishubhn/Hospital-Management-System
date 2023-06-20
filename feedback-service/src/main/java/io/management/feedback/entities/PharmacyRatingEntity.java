package io.management.feedback.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Entity
@Table(name = "PharmacyRatings")
public class PharmacyRatingEntity {
	@Id
	private String ratingsId;
	private String medicineId;
	private String userId;  // one who gave the rating
	@Min(value = 0)
	@Max(value = 5)
	private int ratings;
	@Size(max = 300)
	private String feedback;

	public PharmacyRatingEntity(String medicineId, String userId, int ratings, String feedback) {
		this.medicineId = medicineId;
		this.userId = userId;
		this.ratings = ratings;
		this.feedback = feedback;
	}
}
