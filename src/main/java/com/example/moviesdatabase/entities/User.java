package com.example.moviesdatabase.entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String userName;

    @Column
    @NotBlank
    private String password;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private LocalDate dateOfBirth;

    @Transient
    private Integer age;

    /*public RegUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public RegUser() {

    }*/

    public User() {
    }

    public User(String userName, String password, String email, LocalDate dateOfBirth/*, Integer age*/) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        /*this.age = age;*/
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
