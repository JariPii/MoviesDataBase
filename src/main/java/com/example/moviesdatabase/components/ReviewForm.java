package com.example.moviesdatabase.components;

import com.example.moviesdatabase.entities.Review;
import com.example.moviesdatabase.services.ReviewService;
import com.example.moviesdatabase.view.ReviewPage;
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
    //TextField points = new TextField("Points");
    //Button editReview = new Button("Edit");
    Button saveButton = new Button("Save");

    Binder<Review> binder = new BeanValidationBinder<>(Review.class);
    ReviewService reviewService;
    ReviewPage reviewPage;

    public ReviewForm(ReviewService reviewService, ReviewPage reviewPage) {
        this.reviewService = reviewService;
        this.reviewPage = reviewPage;
        binder.bindInstanceFields(this);
        setVisible(false);

        saveButton.addClickListener(e -> saveReview());

        add(revTitle, revMessage/*, points*/, saveButton);

    }

    private void saveReview() {
        Review review = binder.validate().getBinder().getBean();
        if(review.getId() == 0){
            reviewService.save(review);
        } else {
            reviewService.updateById(review.getId(), review);
        }
        setReview(null);
        reviewPage.updateReview();

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
