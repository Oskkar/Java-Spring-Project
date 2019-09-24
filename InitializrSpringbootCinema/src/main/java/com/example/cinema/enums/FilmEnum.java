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
public enum FilmEnum {
    
    NIEZNISZCZALNI("Sensacja"),
    DOOM("Horror"),
    ATLANTYDA("Przygodowy"),
    SNIPER("Wojenny");
    
    private String titleFilm;

    private FilmEnum(String titleFilm) {
        this.titleFilm = titleFilm;
    }

    public String getTitleFilm() {
        return titleFilm;
    }
      
}
