package com.example.moviesdatabase.entities;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String revTitle;

    @Column
    private String revMessage;

    @Column
    private int revPoints;

    public Review() {
    }

    public Review(String revTitle, String revMessage, int revPoints) {
        this.revTitle = revTitle;
        this.revMessage = revMessage;
        this.revPoints = revPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevTitle() {
        return revTitle;
    }

    public void setRevTitle(String revTitle) {
        this.revTitle = revTitle;
    }

    public String getRevMessage() {
        return revMessage;
    }

    public void setRevMessage(String revMessage) {
        this.revMessage = revMessage;
    }

    public int getRevPoints() {
        return revPoints;
    }

    public void setRevPoints(int revPoints) {
        this.revPoints = revPoints;
    }
}
