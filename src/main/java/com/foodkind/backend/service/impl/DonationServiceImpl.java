package com.foodkind.backend.service.impl;

import com.foodkind.backend.dto.CreateDonationRequest;
import com.foodkind.backend.dto.DonationResponse;
import com.foodkind.backend.entity.Donation;
import com.foodkind.backend.entity.DonationStatus;
import com.foodkind.backend.entity.User;
import com.foodkind.backend.exception.ResourceNotFoundException;
import com.foodkind.backend.repository.DonationRepository;
import com.foodkind.backend.repository.UserRepository;
import com.foodkind.backend.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public DonationResponse createDonation(CreateDonationRequest request, Long donorId) {
        User donor = userRepository.findById(donorId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", donorId));

        Donation donation = Donation.builder()
                .foodItem(request.getFoodItem())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .pickupLocation(request.getPickupLocation())
                .suitableFor(request.getSuitableFor())
                .donor(donor)
                .status(DonationStatus.PENDING)
                .build();

        Donation savedDonation = donationRepository.save(donation);
        return mapToResponse(savedDonation);
    }

    @Override
    public List<DonationResponse> getAllDonations() {
        return donationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonationResponse> getDonationsByStatus(DonationStatus status) {
        return donationRepository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DonationResponse updateDonationStatus(Long donationId, DonationStatus status, Long userId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));
        
        donation.setStatus(status);
        return mapToResponse(donationRepository.save(donation));
    }

    @Override
    @Transactional
    public DonationResponse assignReceiver(Long donationId, Long receiverId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", receiverId));
        
        donation.setReceiver(receiver);
        donation.setStatus(DonationStatus.ACCEPTED);
        return mapToResponse(donationRepository.save(donation));
    }

    @Override
    @Transactional
    public DonationResponse assignVolunteer(Long donationId, Long volunteerId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));
        User volunteer = userRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", volunteerId));
        
        donation.setVolunteer(volunteer);
        return mapToResponse(donationRepository.save(donation));
    }

    private DonationResponse mapToResponse(Donation donation) {
        return DonationResponse.builder()
                .id(donation.getId())
                .foodItem(donation.getFoodItem())
                .quantity(donation.getQuantity())
                .description(donation.getDescription())
                .pickupLocation(donation.getPickupLocation())
                .suitableFor(donation.getSuitableFor())
                .status(donation.getStatus())
                .donorId(donation.getDonor().getId())
                .donorName(donation.getDonor().getFirstName() + " " + donation.getDonor().getLastName())
                .receiverId(donation.getReceiver() != null ? donation.getReceiver().getId() : null)
                .receiverName(donation.getReceiver() != null ? donation.getReceiver().getFirstName() + " " + donation.getReceiver().getLastName() : null)
                .volunteerId(donation.getVolunteer() != null ? donation.getVolunteer().getId() : null)
                .volunteerName(donation.getVolunteer() != null ? donation.getVolunteer().getFirstName() + " " + donation.getVolunteer().getLastName() : null)
                .createdAt(donation.getCreatedAt())
                .build();
    }
}
