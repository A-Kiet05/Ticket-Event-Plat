package com.example.ticketEvent.repositories;

import java.util.UUID;

import com.example.ticketEvent.domain.entities.QrCodeStatus;
import com.example.ticketEvent.domain.entities.TicketValidationStatus;

import java.util.Optional;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
    
    Optional<QrCode> findByTicketIdAndTicketPurchaserId(UUID ticketId , UUID purchaserId);
    Optional<QrCode> findByTicketIdAndStatus(UUID ticketId , QrCodeStatus status);
    Optional<QrCode> findByIdAndStatus(UUID id , QrCodeStatus status);
}