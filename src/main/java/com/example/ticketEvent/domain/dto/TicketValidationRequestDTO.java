package com.example.ticketEvent.domain.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor

public class TicketValidationRequestDTO{
    
   private UUID id; //either ticket or qr code id 
   private TicketValidationMethod validationMethod;

}