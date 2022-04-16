package com.example.moviesdatabase.entities;

import javax.persistence.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String revTitle;

    @Column
    private String revMessage;

    /*@Column
    private int revPoints;*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie title;

    public Review() {
    }

    public Review(String revTitle, String revMessage/*, int revPoints*/, User userName, Movie title) {
        this.revTitle = revTitle;
        this.revMessage = revMessage;
        //this.revPoints = revPoints;
        this.userName = userName;
        this.title = title;
    }

    public Movie getMovie() {
        return title;
    }

    public void setMovie(Movie title) {
        this.title = title;
    }

    public User getUser() {
        return userName;
    }

    public void setUser(User userName) {
        this.userName = userName;
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

    /*public int getRevPoints() {
        return revPoints;
    }

    public void setRevPoints(int revPoints) {
        this.revPoints = revPoints;
    }*/
}
