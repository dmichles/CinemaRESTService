package com.example.cinemarestservice.service;

import com.example.cinemarestservice.model.Stats;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    public Stats stats;

    public StatisticsService() {
        this.stats = new Stats();
    }

    public void addIncome(int ticket_price) {
        this.stats.setCurrent_income(this.stats.getCurrent_income() + ticket_price);
    }

    public void subtractIncome(int ticket_price) {
        this.stats.setCurrent_income(this.stats.getCurrent_income() - ticket_price);
    }

    public void getAvailableSeats(int available_seats){
        this.stats.setNumber_of_available_seats(available_seats);
    }

    public void getPurchasedTickets(int purchased_tickets){
        this.stats.setNumber_of_purchased_tickets(purchased_tickets);
    }

}
