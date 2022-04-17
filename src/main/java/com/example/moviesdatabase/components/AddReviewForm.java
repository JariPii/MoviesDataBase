package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.view.MoviePage;
import com.example.moviesdatabase.view.ReviewPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class AddReviewForm extends FormLayout {

    Binder<Review> binder = new BeanValidationBinder<>(Review.class);
    ReviewService reviewService;
    MoviePage moviePage;

    TextField revTitle = new TextField("Review title");
    TextArea revMessage = new TextArea("Review");


    Button save = new Button("Save");
    Review review;

    public AddReviewForm(ReviewService reviewService, MoviePage moviePage) {
        this.reviewService = reviewService;
        this.moviePage = moviePage;
        binder.bindInstanceFields(this);
        setVisible(false);

        add(revTitle, revMessage, save);

        save.addClickListener(e -> saveReview());
    }

    private void saveReview() {
        Review review = binder.validate().getBinder().getBean();

        if(review.getId() == 0){
            reviewService.save(review);
            UI.getCurrent().navigate("revs");
        } else {
            reviewService.updateById(review.getId(), review);
        }

        setReview(null);

        //reviewView.updateItems();
        //reviewPage.updateReview();

        this.getParent().ifPresent(c -> {
            if( c instanceof Dialog) {
                ((Dialog) c).close();
            }
        });

    }

    public void setReview(Review review) {
        if(review != null){
            binder.setBean(review);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}
