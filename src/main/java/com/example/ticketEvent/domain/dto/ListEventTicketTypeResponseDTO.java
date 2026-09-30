package com.example.ticketEvent.domain.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ListEventTicketTypeResponseDTO {
    
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
    
}
