/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.controller;

import com.example.cinema.enums.FilmEnum;
import com.example.cinema.model.CinemaRoom;
import com.example.cinema.model.Film;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.CinemaRoomRepository;
import com.example.cinema.repository.FilmRepository;
import com.example.cinema.repository.TicketRepository;
import java.util.EnumSet;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adam Przedlacki
 */
@Controller
public class WebController {
    
    
    @Autowired 
    FilmRepository filmRepository;
    
    @Autowired
    TicketRepository ticketRepository;
    
    @Autowired 
    CinemaRoomRepository cinemaRoomRepository;
    
    @RequestMapping("/index")
    public String homes(Model model, Ticket ticket){
            model.addAttribute("films", filmRepository.findAll());
            model.addAttribute(ticket);
            return "index";
    }
    
    @GetMapping("/reservations")
    public String showOrderss(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());
        return("reservations");
    }
    
    @PostMapping("/reservations")
    public String showReservationss(Model model, @Valid Ticket ticket, @RequestParam Long filmId){
        
        ticket.setCinema(cinemaRoomRepository.findOne(filmId));
        
        if(ticket.getTypeOfFilm().equals("1D"))
            ticket.setPrice(15);
        else
            ticket.setPrice(20);
        
        ticket.setFilms(filmRepository.findOne(filmId));
        ticketRepository.saveAndFlush(ticket);
        
        model.addAttribute("tickets", ticketRepository.findAll());
        return "reservations";
    }
    
}
