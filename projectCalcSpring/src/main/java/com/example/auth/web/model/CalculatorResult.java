/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Oskar Nalaskowski
 */
@Entity
@Table(name = "calculator")
@Data
@Getter @Setter
@EnableTransactionManagement
public class CalculatorResult implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dataWyjazdu;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "HH:mm")
    private Date godzinaWyjazdu;
   
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPrzyjazdu;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "HH:mm")
    private Date godzinaPrzyjazdu;
    
    private float dietaPodrozy;
    
    private int iloscSniadan, iloscObiadow, iloscKolacji;
    /*
    Dojazd
    */
    
    private String srodekTransportu;
    private float cenaSrodekTransportu;
    private String pojazd;
    private int iloscKilometrow;
    
    /*
    Dojazd Komunikacja
    */

    private int iloscDobDojazd;
    private int cenaBiletu;
    
    /*
    Noclegi
    */
    private int liczbaNoclegow;
    private float cenaNoclegu; 
    
   @ManyToOne
   @JoinColumn(name = "userId")
   private User userId;
   
    /*
    **************
    **************
    */
    private String czasPodrozy;
    private float kosztPodrozy;
    private float ryczaltZaDojazdy;
    private float noclegRyczalt;
    private float kwotaZmniejszajacaDiety=0;
    private float roznicaDiet;
    private float sumaKosztow = 0;
    private float dietaPodrozySum = 0;
   
    public CalculatorResult(Date dataWyjazdu, Date godzinaWyjazdu, Date dataPrzyjazdu, 
            Date godzinaPrzyjazdu, float dietaPodrozy, int iloscSniadan, int iloscObiadow, 
            int iloscKolacji, String srodekTransportu, float cenaSrodekTransportu, String pojazd, 
            int iloscKilometrow, int iloscDobDojazd, int cenaBiletu, int liczbaNoclegow, 
            float cenaNoclegu, String czasPodrozy, float kosztPodrozy, float ryczaltZaDojazdy, 
            float noclegRyczalt, float roznicaDiet, float dietaPodrozySum, float sumaKosztow, User userId) {
        this.dataWyjazdu = dataWyjazdu;
        this.godzinaWyjazdu = godzinaWyjazdu;
        this.dataPrzyjazdu = dataPrzyjazdu;
        this.godzinaPrzyjazdu = godzinaPrzyjazdu;
        this.dietaPodrozy = dietaPodrozy;
        this.iloscSniadan = iloscSniadan;
        this.iloscObiadow = iloscObiadow;
        this.iloscKolacji = iloscKolacji;
        this.srodekTransportu = srodekTransportu;
        this.cenaSrodekTransportu = cenaSrodekTransportu;
        this.pojazd = pojazd;
        this.iloscKilometrow = iloscKilometrow;
        this.iloscDobDojazd = iloscDobDojazd;
        this.cenaBiletu = cenaBiletu;
        this.liczbaNoclegow = liczbaNoclegow;
        this.cenaNoclegu = cenaNoclegu;
        this.czasPodrozy = czasPodrozy;
        this.kosztPodrozy = kosztPodrozy;
        this.ryczaltZaDojazdy = ryczaltZaDojazdy;
        this.noclegRyczalt = noclegRyczalt;
        this.roznicaDiet = roznicaDiet;
        this.dietaPodrozySum = dietaPodrozySum;
        this.sumaKosztow = sumaKosztow;
        this.userId = userId;
    }

    public CalculatorResult() {
    }   

}
