package com.example.cinemarestservice.service;

import com.example.cinemarestservice.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SeatService {
    private List<Seat> seats;

    public SeatService() {
        this.seats = generate();
    }

    public List<Seat> generate() {
        List<Seat> all_seats = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                Seat s = new Seat();
                s.setRow(i);
                s.setColumn(j);
                if (i <= 4) {
                    s.setPrice(10);
                } else {
                    s.setPrice(8);
                }
                s.setTaken(false);
                all_seats.add(s);
            }
        }
        return all_seats;
    }

    public List<Seat> list() {
        List<Seat> available_seats = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 81; i++) {
            if (seats.get(i).getTaken() == false) {
                available_seats.add(seats.get(i));
            }
        }
        return available_seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}

