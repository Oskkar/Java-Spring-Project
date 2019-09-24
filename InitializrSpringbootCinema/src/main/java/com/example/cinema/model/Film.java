/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Film implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title; 
    
    private String typeOfFilm;

    public Film(String title, String typeOfFilm) {
        this.title = title;
        this.typeOfFilm = typeOfFilm;
    }


    @OneToMany(mappedBy="films")
    List<Ticket> tickets;
   
   
}
