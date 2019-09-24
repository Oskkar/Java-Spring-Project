package com.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@Getter @Setter
public class User {
    
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(unique=true)
  private String username;
  
  private String nazwaFirmy;
  
  private String password;
  
  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name = "user_role",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;
  
   /*@JsonIgnore
   @OneToMany(mappedBy ="userId")
   private List<CalculatorResult> userId;*/
}
