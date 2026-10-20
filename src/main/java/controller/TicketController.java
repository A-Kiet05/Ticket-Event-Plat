package com.example.ticketEvent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketEvent.mappers.TicketMapper;
import com.example.ticketEvent.service.TicketService;


@RestController
@RequestMapping (path = "/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController{

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    
    @GetMapping
    public Page<ListTicketResponseDTO> getTicket (@AuthenticationPrincipal Jwt jwt , Pageable pageable){

        return ticketService.viewTickets(parseUserId(jwt) , pageable).map(ticketMapper::toListTicketResponseDTO);
    }

}