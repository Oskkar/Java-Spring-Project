/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Adam Przedlacki
 */

@Entity
@Getter @Setter
@NoArgsConstructor
public class Ticket implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String typeOfFilm;
    private int price;

    public Ticket(String customerName, String typeOfFilm, int price) {
        this.customerName = customerName;
        this.typeOfFilm = typeOfFilm;
        this.price = price;
    }

    
    
    @OneToOne
    @JoinColumn(name = "FILM_ID")
    private Film films;
    
    @ManyToOne
    @JoinColumn(name = "CINEMA_ROOM_ID")
    private CinemaRoom cinema;
 
    
}
