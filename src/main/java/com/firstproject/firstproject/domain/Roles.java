package com.firstproject.firstproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roles {
    
  @Id
  @GeneratedValue
  private Integer id;
  private String name;

  public Roles(Integer id) {
    this.id = id;
  }

  

}
