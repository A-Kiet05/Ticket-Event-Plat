package com.example.ticketEvent.repositories;


@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
    
}