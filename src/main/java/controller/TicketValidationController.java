package com.example.ticketEvent.controller;

import com.example.ticketEvent.domain.dto.TicketValidationRequestDTO;
import com.example.ticketEvent.domain.entities.TicketValidationMethod;
import com.example.ticketEvent.service.TicketValidationService;

@RequestMapping(path= "/api/v1/ticket-validations")
@RestController
@RequiredArgsConstructor
public class TicketValidationController{

    private final TicketValidationService ticketValidationService;
    private final TicketValidationMapper ticketValidationMapper;

    @PostMapping
    public ResponseEntity<TicketValidationResponseDTO> verifyTicket 
    (
        @RequestBody TicketValidationRequestDTO ticketValidationRequestDto
    ){
        TicketValidationMethod ticketValidationMethod = ticketValidationRequestDto.getValidationMethod();
        TicketValidation ticketValidation ;
        if(ticketValidationMethod.equals(TicketValidationMethod.QR_CODE)){

            ticketValidation = ticketValidationService.verifyByQrCodeId(ticketValidationRequestDto.getId());

        }else{
             ticketValidation =ticketValidationService.verifyByTicketId(ticketValidationRequestDto.getId());
        }

        return ResponseEntity.ok(ticketValidationMapper.getTicketValidationResponseDto(ticketValidation));

    }
}