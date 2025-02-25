package com.example.AppProj;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;


//Entity Class
@Entity(name= "bloodetails")
public class BlooDetails {

    @Id//Applied to a field in the entity class that should be used as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)// specifies that the database should handle the generation of the id value automatically using its identity column feature.
    Long id;

    @Column(name = "name")
    String Name;
    
    @Column(name = "age")
    Long Age;
    
    @Column(name = "gender")
    String Gender;
    
    @Column(name = "phone")
    String Phone;
    
    @Column(name = "bgroup")
    String BGroup;
    
    @Column(name = "city")
    String City;
    
    @Column(name = "dishis")
   	String DisHis;
    
    @Column(name = "email")
   	String Email;
    
    //Connecting this column id to BloodBank id, the bloodetails id column is set as both Primary and Foreign key
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")  // Allowing JPA to manage the foreign key
    private BloodBank bloodbank;
    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public Long getAge() {
        return Age;
    }

    public void setAge(Long Age) {
        this.Age = Age;
    }
    
    public String getGender() {
        return Gender;
    }

    public void setGender(String gen) {
        this.Gender = gen;
    }
    
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }
    
    public String getBGroup() {
        return BGroup;
    }

    public void setBGroup(String bg) {
        this.BGroup = bg;
    }
    
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }
    
    public String getDisHis() {
        return DisHis;
    }

    public void setDisHis(String dishis) {
        this.DisHis = dishis;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
    

	public BloodBank getBloodbank() {
	    return bloodbank;
	}
	
	public void setBloodbank(BloodBank bloodbank) {
	    this.bloodbank = bloodbank;
	}
}