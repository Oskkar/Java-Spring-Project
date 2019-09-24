/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.configuration;

import com.example.cinema.enums.FilmEnum;
import com.example.cinema.enums.CinemaRoomEnum;
import com.example.cinema.model.CinemaRoom;
import com.example.cinema.model.Film;
import com.example.cinema.repository.CinemaRoomRepository;
import com.example.cinema.repository.FilmRepository;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Adam Przedlacki
 */
@Configuration
public class CinemaConfig {
    
    @Autowired
    FilmRepository filmRepository;
    
    @Autowired 
    CinemaRoomRepository cinemaRoomRepository;
    
    @Bean
    public CinemaRoomRepository addChamber(){
        EnumSet.allOf(CinemaRoomEnum.class)
                .forEach(iterator -> cinemaRoomRepository.save(new CinemaRoom(iterator.toString())));
        return cinemaRoomRepository;
    }
    
    @Bean
    public FilmRepository addFilm(){
      
        EnumSet.allOf(FilmEnum.class) 
                .forEach(iterator -> filmRepository.save(new Film(iterator.toString(),
                        iterator.getTitleFilm())));
        return filmRepository;
    }
    
    
}
