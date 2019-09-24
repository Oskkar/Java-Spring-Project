/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.auth.web.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Oskar Nalaskowski
 */
@Getter @Setter
public class Calculator {
    
        /*
    Diety
    */
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataWyjazdu;
    
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date godzinaWyjazdu;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    
    private Date dataPrzyjazdu;
    
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date godzinaPrzyjazdu;
    
    @Min(30)
    private float dietaPodrozy;
    
    private int iloscSniadan, iloscObiadow, iloscKolacji;
    
    /*
    Dojazd
    */
    private String srodekTransportu;
    private float cenaSrodekTransportu;
    private String pojazd;
    
    @Min(1)
    private int iloscKilometrow;
    
    /*
    Dojazdy komunikacja
    */
    private int iloscDobDojazd;
    
    @Min(1)
    private int cenaBiletu;
    
    /*
    Noclegi
    */
    @Min(0)
    private int liczbaNoclegow;
    
    @Min(0)
    private float cenaNoclegu;
    
    
    /*
    
    */
    private String czasPodrozy;
    private float kosztPodrozy;
    private float ryczaltZaDojazdy;
    private float noclegRyczalt;
    private float kwotaZmniejszajacaDiety=0;
    private float roznicaDiet;
    private float sumaKosztow = 0;
    private float dietaPodrozySum = 0;
    
    
    public float obliczRoznicaDiet(float dietaPodrozySum, float kwotaZmniejszajacaDiety){
        roznicaDiet = dietaPodrozySum - kwotaZmniejszajacaDiety;
        System.out.println(dietaPodrozySum + " " + kwotaZmniejszajacaDiety);
        sumaKosztow += roznicaDiet;
        return roznicaDiet;
    }
    
    public float zmniejszDiety(float dietaPodrozy, int iloscSniadan, int iloscObiadow, int iloscKolacji){
        
        if(iloscSniadan != 0){
            kwotaZmniejszajacaDiety += iloscSniadan * dietaPodrozy / 4;
            sumaKosztow+= kwotaZmniejszajacaDiety;
        }
        if(iloscObiadow != 0){
            kwotaZmniejszajacaDiety += iloscObiadow * dietaPodrozy / 2;
            sumaKosztow+= kwotaZmniejszajacaDiety;
            
        }
        if(iloscKolacji != 0){
            kwotaZmniejszajacaDiety += iloscKolacji * dietaPodrozy / 4;
            sumaKosztow+= kwotaZmniejszajacaDiety;
        }
        
        return kwotaZmniejszajacaDiety;
    }
    
    public float obliczWysokoscDiety(float dietaPodrozy, Date dataWyjazdu, Date dataPrzyjazdu,
           Date godzinaWyjazdu, Date godzinaPrzyjazdu){
        long help; 
           
        dataWyjazdu.setHours(godzinaWyjazdu.getHours());
        dataWyjazdu.setMinutes(godzinaWyjazdu.getMinutes());
        
        dataPrzyjazdu.setHours(godzinaPrzyjazdu.getHours());
        dataPrzyjazdu.setMinutes(godzinaPrzyjazdu.getMinutes());

        long iloscDni, iloscGodzin, iloscMinut;
        iloscDni = ((long) dataPrzyjazdu.getTime() - (long) dataWyjazdu.getTime() ) / 1000 / 60 / 60 / 24;
        iloscGodzin = ((long) dataPrzyjazdu.getTime() - (long) dataWyjazdu.getTime() ) / 1000 / 60 / 60 - 24 ;
        iloscMinut = (iloscGodzin * 60 + godzinaPrzyjazdu.getMinutes() + godzinaWyjazdu.getMinutes())-(iloscGodzin * 60);
        
        float dietaPodrozySum =0;
        help = iloscDni * 24 + iloscGodzin;
        for(long i = 0; i <help; i++){
            if (i % 24 ==0)
                dietaPodrozySum += 0;
            if(i % 24 ==8)
                dietaPodrozySum+= dietaPodrozy / 2;
            if(i % 24 == 13)
                dietaPodrozySum+= dietaPodrozy;
        }
        return dietaPodrozySum;
    }
    public float obliczRyczaltNoclegu(int liczbaNoclegow){
        noclegRyczalt = 45 * liczbaNoclegow;
        sumaKosztow+= noclegRyczalt;
        return noclegRyczalt;
    }
    
    public float obliczRyczaltZaDojazdy(int iloscDobDojazd){
        ryczaltZaDojazdy = iloscDobDojazd * 6;
        sumaKosztow+= ryczaltZaDojazdy;
        return ryczaltZaDojazdy;
    }
    
    
    public String czasPodrozyToString(Date dataWyjazdu, Date dataPrzyjazdu,
           Date godzinaWyjazdu, Date godzinaPrzyjazdu){
        
        dataWyjazdu.setHours(godzinaWyjazdu.getHours());
        dataWyjazdu.setMinutes(godzinaWyjazdu.getMinutes());
        
        dataPrzyjazdu.setHours(godzinaPrzyjazdu.getHours());
        dataPrzyjazdu.setMinutes(godzinaPrzyjazdu.getMinutes());

        long iloscDni, iloscGodzin, iloscMinut;
        iloscDni = ((long) dataPrzyjazdu.getTime() - (long) dataWyjazdu.getTime() ) / 1000 / 60 / 60 / 24;
        iloscGodzin = ((long) dataPrzyjazdu.getTime() - (long) dataWyjazdu.getTime() ) / 1000 / 60 / 60 % 24 ;
        
        
        czasPodrozy = iloscDni + "d " + iloscGodzin + "h ";
        
        return czasPodrozy;
    }
    
    public float obliczKosztPodrozyPojazdem(int iloscKilometrow, String pojazd){
        if(pojazd.equals("Samochód o pojemności do 900cm3"))
            kosztPodrozy = (float) (iloscKilometrow * 0.52);
        else if(pojazd.equals("Samochód o pojemności powyżej 900cm3"))
            kosztPodrozy = (float) (iloscKilometrow * 0.84); 
        else if(pojazd.equals("Motocykl"))
            kosztPodrozy = (float) (iloscKilometrow * 0.23);
        else if(pojazd.equals("Motorower")){
            kosztPodrozy = (float) (iloscKilometrow * 0.14);
        }
        sumaKosztow+= kosztPodrozy;
        return kosztPodrozy;
    }
    
}
