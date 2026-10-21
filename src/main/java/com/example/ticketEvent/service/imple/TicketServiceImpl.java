package com.example.ticketEvent.service;

import com.example.ticketEvent.domain.entities.User;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    

    @Override
    public Page<Ticket> viewTickets(UUID userId, Pageable pageable) {

        
        return ticketRepository.findByPurchaserId(userId , pageable);
    }
    
    @Override 
    public Optional<Ticket> getTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @Override
    public Optional<Ticket> getTicketForUser(UUID ticketId , UUID userId){
        return ticketRepository.findByIdAndPurchaserId(ticketId , userId);
    }
    
}