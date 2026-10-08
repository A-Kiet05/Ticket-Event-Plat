package com.example.ticketEvent.controller;



@RestController
@RequestMapping("/api/v1/events/{eventId}/ticket-types")
@RequiredArgsConstructor

public class TicketTypeController {
    
    private final TicketTypeService ticketTypeService;
    private final TicketService ticketService;

    @PostMapping("/{ticketTypeId}/purchase")
    public ResponseEntity<Void> purchaseTicket(
                                                    @PathVariable UUID ticketTypeId, 
                                                    @AuthenticationPrincipal Jwt jwt) {
        
        UUID userId = JwtUtil.parseUserId(jwt);
        Ticket purchasedTicket = ticketService.purchaseTicket(userId, ticketTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 