package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostTicketDTO;
import ru.ndavs.atp.Services.TicketService;

@RestController
@RequestMapping("/apishechka/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class TicketController {
    private final TicketService ticketService;
    @GetMapping
    private ResponseEntity getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity getTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    private ResponseEntity addNewTicket(@RequestBody PostTicketDTO postTicketDTO){
        return ResponseEntity.ok(ticketService.addNewTicket(postTicketDTO));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity updateTicketById(@RequestBody PostTicketDTO postTicketDTO, @PathVariable Long id){
        return ResponseEntity.ok(ticketService.updateTicketById(postTicketDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity deleteTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.deleteTicketById(id));
    }
}
