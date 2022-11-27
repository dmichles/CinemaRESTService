package com.example.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Seat {
    private Integer row;


    private Integer column;

    private Integer price;

    @JsonIgnore
    private Boolean taken;

    public Seat(){

    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
}