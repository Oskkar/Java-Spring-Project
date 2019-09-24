/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.enums;

/**
 *
 * @author Adam Przedlacki
 */
public enum CinemaRoomEnum {
    
    SALA1("1"), SALA2("2"), SALA3("3"), SALA4("4");
    
    
    private String sala; 

    private CinemaRoomEnum(String sala) {
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    } 
    
}
