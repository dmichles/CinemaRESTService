package com.example.cinemarestservice.service;

import com.example.cinemarestservice.model.Seat;
import com.example.cinemarestservice.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TicketService {
    private Ticket currentTicket;
    private List<Ticket> tickets;

    public TicketService() {
        this.currentTicket = new Ticket();
        this.tickets = new CopyOnWriteArrayList<>();
    }

    public void addTicket(Seat seat){
        this.currentTicket.setToken(UUID.randomUUID().toString());
        this.currentTicket.setTicket(seat);
        tickets.add(this.currentTicket);
    }

    public int returnTicket(Ticket ticket){
        for (Ticket t: tickets) {
            if (t.getToken().equals(ticket.getToken())){
                tickets.remove(t);
                int index = 9*(t.getTicket().getRow()-1)+t.getTicket().getColumn()-1;
                return index;
            }
        }
        return -1;
    }

    public Ticket getCurrentTicket() {
        return this.currentTicket;
    }

    public void setCurrentTicket(Ticket currentTicket) {
        this.currentTicket = currentTicket;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}