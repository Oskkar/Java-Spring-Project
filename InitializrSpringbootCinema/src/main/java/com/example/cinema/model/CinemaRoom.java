/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class CinemaRoom {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numberOfRoom;
    
    private int numberOfSeats = 2;

    public CinemaRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }
    
    @OneToMany(mappedBy="cinema")
    List<Ticket> tickets;
    
}
