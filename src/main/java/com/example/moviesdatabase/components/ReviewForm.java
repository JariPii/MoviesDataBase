package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.services.UserService;
import com.example.moviesdatabase.view.MoviePage;
import com.example.moviesdatabase.view.ReviewView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class ReviewForm extends FormLayout {

    TextField revTitle = new TextField("Title");
    TextArea revMessage = new TextArea("My review");
    TextField title = new TextField("Movie");
    //IntegerField rating = new IntegerField("Rate movie");
    //Button editReview = new Button("Edit");
    Button saveButton = new Button("Save");

    Binder<Review> binder = new BeanValidationBinder<>(Review.class);
    ReviewService reviewService;
    UserService userService;
    ReviewView reviewView;


   public ReviewForm(ReviewService reviewService, ReviewView reviewView) {
        this.reviewService = reviewService;
        this.reviewView = reviewView;
        binder.bindInstanceFields(this);
        setVisible(false);



        add(revTitle, revMessage,title, saveButton);

       saveButton.addClickListener(e -> saveReview());

    }

    private void saveReview() {
        Review review = binder.validate().getBinder().getBean();

        if(review.getId() == 0){
            reviewService.save(review);
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
