package io.management.feedback.entities.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRatingsResponse {
    private String ratingId;
    private String doctorId;
    private String userId;  // one who gave the rating
    private int ratings;
    private String feedback;
}
