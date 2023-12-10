package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.GetTicketByDate;
import ru.ndavs.atp.DTO.PostTicketDTO;
import ru.ndavs.atp.Services.TicketService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/apishechka/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class TicketController {
    private final TicketService ticketService;
    @GetMapping
    private ResponseEntity<?> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<?> getTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
    @GetMapping("/get_by_date")
    private ResponseEntity<?> getTicketByDate(@RequestBody GetTicketByDate getTicketByDate){
        return ResponseEntity.ok(ticketService.getTicketsByDate(getTicketByDate.getDate()));
    }

    @GetMapping("/get_by_departure/{id}")
    private ResponseEntity<?> getTicketByDeparture(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicketsByDeparture(id));
    }

    @PostMapping
    private ResponseEntity<?> addNewTicket(@RequestBody PostTicketDTO postTicketDTO){
        return ResponseEntity.ok(ticketService.addNewTicket(postTicketDTO));
    }
    @PostMapping("/few")
    private ResponseEntity<?> addFewTicket(@RequestBody List<PostTicketDTO> postTicketDTO){
        return ResponseEntity.ok(ticketService.addFewTickets(postTicketDTO));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<?> updateTicketById(@RequestBody PostTicketDTO postTicketDTO, @PathVariable Long id){
        return ResponseEntity.ok(ticketService.updateTicketById(postTicketDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> deleteTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.deleteTicketById(id));
    }
}
