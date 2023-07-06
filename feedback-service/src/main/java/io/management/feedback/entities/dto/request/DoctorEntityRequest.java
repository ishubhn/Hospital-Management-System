package io.management.feedback.entities.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEntityRequest {
    private String doctorId;
    private String userId;  // one who gave the rating
    private int ratings;
    private String feedback;
}
