package com.example.ticketEvent.service;

@RequiredArgsConstructor  
@Service
public class TicketTypeServiceImpl implements TicketService {

    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException( 
                        String.format("User with ID %s was not found", userId)
                    ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
                .orElseThrow(() -> new TicketTypeNotFoundException( 

                       String.format("Ticket type with ID %s was not found", ticketTypeId)
                    ));

       
        
        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketTypeId);
        Integer availableTickets = ticketType.getTotalAvailable();

        if(purschasedTickets + 1 > availableTickets){

            throw new TicketSoldOutException();
        }

        // Create a new ticket
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketType(ticketType);
        ticket.setStatus(TicketStatus.PURCHASED);

        // Generate QR code for the ticket
        Ticket savedTicket = ticketRepository.save(ticket);
         qrCodeService.generateQrCode(savedTicket);
        

        

        return ticketRepository.save(savedTicket);
    }
}