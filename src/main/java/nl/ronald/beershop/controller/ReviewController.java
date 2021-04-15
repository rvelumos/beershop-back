package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Review;
import nl.ronald.beershop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    //fix product relation
    @GetMapping("/reviews")
    public ResponseEntity<Object> getReviews() {
        List<Review> review = reviewRepository.findAll();
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    //fix product relation
    @GetMapping(value="/reviews/{id}")
    public ResponseEntity<Object> getReview(@PathVariable("id") long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    //auth fix!
    @PostMapping(value="/reviews/")
    public ResponseEntity<Object> createReview(@RequestBody Review review) {
        reviewRepository.save(review);
        URI location;
        return new ResponseEntity<>("Review toegevoegd", HttpStatus.CREATED);
    }

    // auth check & related authorities fix
    @PutMapping(value = "/admin/review/{id}")
    public Review updateReview(@RequestBody Review review, @PathVariable Long id) {
        return reviewRepository.findById(id)
            .map(updateReview -> {
                updateReview.setCustomer_id(review.getCustomer_id());
                updateReview.setMessage(review.getMessage());
                updateReview.setProduct_id(review.getProduct_id());
                updateReview.setReview_status(review.getReview_status());
                updateReview.setStars_overall(review.getStars_overall());
                updateReview.setStars_price(review.getStars_price());
                updateReview.setStars_taste(review.getStars_taste());
                return reviewRepository.save(updateReview);
            }).orElseGet(() -> {
                return reviewRepository.save(review);
            });
    }

    //auth fix!
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") long id) {
        reviewRepository.deleteById(id);
        return new ResponseEntity<>("Review verwijderd", HttpStatus.OK);
    }
}