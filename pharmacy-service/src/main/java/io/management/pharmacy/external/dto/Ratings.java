package io.management.pharmacy.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
	private String ratingsId;
	private String medicineId;
	private String userId;
	private int ratings;
	private String feedback;
}