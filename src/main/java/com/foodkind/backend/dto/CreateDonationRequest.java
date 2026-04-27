package com.foodkind.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDonationRequest {

    @NotBlank(message = "Food item is required")
    private String foodItem;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private String description;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    private String suitableFor;
}
