package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> getAllReviews();
    public Review getReview(long id);
    public void save(Review review);
    public void deleteById(long id);
}
