package com.example.cinemarestservice.model.dto;

import com.example.cinemarestservice.model.Seat;

import java.util.List;

public class DataResponse {
    private Integer total_rows;
    private Integer total_columns;
    private List<Seat> available_seats;

    public DataResponse(){
    }

    public Integer getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(Integer total_rows) {
        this.total_rows = total_rows;
    }

    public Integer getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(Integer total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }
}
