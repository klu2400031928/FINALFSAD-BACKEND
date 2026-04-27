package com.foodkind.backend.dto;

import com.foodkind.backend.entity.DonationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationResponse {
    private Long id;
    private String foodItem;
    private Integer quantity;
    private String description;
    private String pickupLocation;
    private String suitableFor;
    private DonationStatus status;
    private Long donorId;
    private String donorName;
    private Long receiverId;
    private String receiverName;
    private Long volunteerId;
    private String volunteerName;
    private LocalDateTime createdAt;
}
