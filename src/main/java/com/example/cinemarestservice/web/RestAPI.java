package com.example.cinemarestservice.web;

import com.example.cinemarestservice.model.Seat;
import com.example.cinemarestservice.model.Ticket;
import com.example.cinemarestservice.model.dto.DataResponse;
import com.example.cinemarestservice.service.SeatService;
import com.example.cinemarestservice.service.StatisticsService;
import com.example.cinemarestservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RestAPI {
    @Autowired
    private SeatService seatService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("/seats")
    public DataResponse getData(){
        List<Seat> list = seatService.list();
        DataResponse response = new DataResponse();
        response.setTotal_rows(9);
        response.setTotal_columns(9);
        response.setAvailable_seats(list);
        return response;
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket (@RequestBody Ticket ticket){
        int index = ticketService.returnTicket(ticket);
        if (index>= 0) {
            List<Seat> list = seatService.getSeats();
            list.get(index).setTaken(false);
            statisticsService.subtractIncome(list.get(index).getPrice());
            statisticsService.stats.setNumber_of_available_seats(seatService.list().size());
            statisticsService.stats.setNumber_of_purchased_tickets(ticketService.getTickets().size());
            return new ResponseEntity<>(Map.of("returned_ticket",list.get(index)),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error","Wrong token!"),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat){
        List<Seat> list = seatService.getSeats();
        int index = 9*(seat.getRow()-1)+seat.getColumn()-1;
        if (seat.getRow() < 1 || seat.getRow() > 9 || seat.getColumn() < 0 || seat.getColumn() > 9) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),HttpStatus.BAD_REQUEST);
        }
        if (!list.get(index).getTaken()) {
            list.get(index).setTaken(true);
            statisticsService.addIncome(list.get(index).getPrice());
            statisticsService.stats.setNumber_of_available_seats(seatService.list().size());
            ticketService.addTicket(list.get(index));
            statisticsService.stats.setNumber_of_purchased_tickets(ticketService.getTickets().size());
            return new ResponseEntity<>(ticketService.getCurrentTicket(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats(@RequestParam(required = false) String password){
        if (password == null) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"),HttpStatus.UNAUTHORIZED);
        } else if (password.equals("super_secret")) {
            return new ResponseEntity<>(statisticsService.stats,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"),HttpStatus.UNAUTHORIZED);
        }
    }
}
