package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Review;
import nl.ronald.beershop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReview(long id) {
        if (reviewRepository.existsById(id)) {
            return reviewRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Review Review) {
        reviewRepository.save(Review);
    }

    @Override
    public void deleteById(long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }


}

