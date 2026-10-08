package com.example.ticketEvent.service;

import com.example.ticketEvent.domain.entities.QrCode;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
    
}