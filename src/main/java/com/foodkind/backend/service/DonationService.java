package com.foodkind.backend.service;

import com.foodkind.backend.dto.CreateDonationRequest;
import com.foodkind.backend.dto.DonationResponse;
import com.foodkind.backend.entity.DonationStatus;

import java.util.List;

public interface DonationService {
    DonationResponse createDonation(CreateDonationRequest request, Long donorId);
    List<DonationResponse> getAllDonations();
    List<DonationResponse> getDonationsByStatus(DonationStatus status);
    DonationResponse updateDonationStatus(Long donationId, DonationStatus status, Long userId);
    DonationResponse assignReceiver(Long donationId, Long receiverId);
    DonationResponse assignVolunteer(Long donationId, Long volunteerId);
}
