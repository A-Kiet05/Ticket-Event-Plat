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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID %s was not found", userId)
                ));

        return ticketRepository.findByUserId(user.getId(), pageable);
    }
    
    @Override 
    public Optional<Ticket> getTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId);
    }
    
}