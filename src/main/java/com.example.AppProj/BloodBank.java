package com.example.AppProj;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

//Entity Class
@Entity(name= "bloodbank")
public class BloodBank {

    @Id//Applied to a field in the entity class that should be used as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)// specifies that the database should handle the generation of the id value automatically using its identity column feature.
    Long id;
    @Column(name = "username")
    String username;
  
    @Column(name = "password")
    String password;


    @OneToOne(mappedBy = "bloodbank", cascade = CascadeType.ALL)
    private BlooDetails bloodetails;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getuser() {
        return username;
    }

    public void setUser(String User) {
        this.username = User;
    }
    
    public String getPass() {
        return password;
    }

    public void setPass(String Pass) {
        this.password = Pass;
    }
    
    public BlooDetails getBloodetails() {
        return bloodetails;
    }

    public void setBloodetails(BlooDetails bloodetails) {
        this.bloodetails = bloodetails;
    }
}
