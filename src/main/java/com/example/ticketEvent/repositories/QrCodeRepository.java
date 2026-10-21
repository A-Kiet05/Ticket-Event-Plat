package com.example.ticketEvent.repositories;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
    
    Optional<QrCode> findByTicketIdAndTicketPurchaserId(UUID ticketId , UUID purchaserId);
}