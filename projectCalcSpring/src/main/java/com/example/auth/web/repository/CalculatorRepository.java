/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.auth.web.repository;

import com.example.auth.web.model.CalculatorResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oskar Nalaskowski
 */
public interface CalculatorRepository extends JpaRepository<CalculatorResult, Long> {
    
}
