package com.foodkind.backend.repository;

import com.foodkind.backend.entity.Donation;
import com.foodkind.backend.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByStatus(DonationStatus status);
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByReceiverId(Long receiverId);
    List<Donation> findByVolunteerId(Long volunteerId);
}
