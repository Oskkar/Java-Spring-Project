/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cinema.repository;

import com.example.cinema.model.CinemaRoom;
import com.example.cinema.model.Film;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Adam Przedlacki
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class FilmRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private FilmRepository filmRepository;
    
    @Test
    public void testFindOne() {
        entityManager.persist(new Film("Rich", "Komedia"));
        Film film = filmRepository.findByTitle("Rich");
        assertEquals("Rich", film.getTitle());
    }
    
}
