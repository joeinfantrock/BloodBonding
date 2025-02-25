package com.example.AppProj;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

//Entity Class
@Entity(name= "practice")
public class Practice {

    @Id//Applied to a field in the entity class that should be used as the primary key.
    private Long id;
    @Column(name = "name")
    private String name;
  //@GeneratedValue(strategy = GenerationType.IDENTITY)// specifies that the database should handle the generation of the id value automatically using its identity column feature.
    // Constructors if needed
    public Practice() {}

    public Practice(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}