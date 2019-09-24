/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.repository;

import com.example.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Adam Przedlacki
 */
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByTitle(String title);
}
