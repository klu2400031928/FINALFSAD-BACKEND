package com.foodkind.backend.controller;

import com.foodkind.backend.dto.ApiResponse;
import com.foodkind.backend.dto.CreateDonationRequest;
import com.foodkind.backend.dto.DonationResponse;
import com.foodkind.backend.entity.DonationStatus;
import com.foodkind.backend.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<ApiResponse<DonationResponse>> createDonation(
            @Valid @RequestBody CreateDonationRequest request,
            @RequestParam Long donorId) {
        DonationResponse response = donationService.createDonation(request, donorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Donation created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DonationResponse>>> getAllDonations(
            @RequestParam(required = false) DonationStatus status) {
        List<DonationResponse> response;
        if (status != null) {
            response = donationService.getDonationsByStatus(status);
        } else {
            response = donationService.getAllDonations();
        }
        return ResponseEntity.ok(ApiResponse.success("Donations fetched successfully", response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DonationResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam DonationStatus status,
            @RequestParam Long userId) {
        DonationResponse response = donationService.updateDonationStatus(id, status, userId);
        return ResponseEntity.ok(ApiResponse.success("Status updated successfully", response));
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<ApiResponse<DonationResponse>> acceptDonation(
            @PathVariable Long id,
            @RequestParam Long receiverId) {
        DonationResponse response = donationService.assignReceiver(id, receiverId);
        return ResponseEntity.ok(ApiResponse.success("Donation accepted successfully", response));
    }

    @PatchMapping("/{id}/assign-volunteer")
    public ResponseEntity<ApiResponse<DonationResponse>> assignVolunteer(
            @PathVariable Long id,
            @RequestParam Long volunteerId) {
        DonationResponse response = donationService.assignVolunteer(id, volunteerId);
        return ResponseEntity.ok(ApiResponse.success("Volunteer assigned successfully", response));
    }
}
