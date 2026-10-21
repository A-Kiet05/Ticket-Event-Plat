package com.example.ticketEvent.controller;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketEvent.mappers.TicketMapper;
import com.example.ticketEvent.service.QrCodeService;
import com.example.ticketEvent.service.TicketService;


@RestController
@RequestMapping (path = "/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController{

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final QrCodeService qrCodeService;
    @GetMapping
    public Page<ListTicketResponseDTO> getTicket (@AuthenticationPrincipal Jwt jwt , Pageable pageable){

        return ticketService.viewTickets(parseUserId(jwt) , pageable).map(ticketMapper::toListTicketResponseDTO);
    }

    @GetMapping(path="{ticketId")
    public ResponseEntity<GetTicketResponseDTO> getTicketForUser(@AuthenticationPrincipal Jwt jwt , @PathVariable UUID ticketId){

        return ticketService.getTicketForUser(parseUserId(jwt)  , ticketId)
               .map(ticketMapper::toGetTicketResponseDTO)
               .map(ResponseEntity::ok)
               .orElse( ResponseEntity.notFound().build());
    }

    @GetMapping(path ="{ticketId}/qr-codes")
    public Response<byte[]> getQrCodeForTicketAndUser(@AuthenticationPrincipal Jwt jwt , @PathVariable UUID ticketId){

        byte[] qrCodeImage = qrCodeService.getQrCodeImageForTicketAndUser(parseUserId(jwt), ticketId);

        HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.IMAGE_PNG);
       headers.setContentLength(qrCodeImage.length);
       
       return ResponseEntity.ok()
              .headers(headers)
              .build();
    }

}