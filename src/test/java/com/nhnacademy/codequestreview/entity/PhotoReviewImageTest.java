package com.nhnacademy.codequestreview.entity;


import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


class PhotoReviewImageTest {

    @Test
    void testPhotoReviewImageCreation() {
        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(LocalDateTime.now());
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        PhotoReviewImage image = new PhotoReviewImage(review, "http://example.com/image1.jpg");

        assertThat(image.getPhotoReview()).isEqualTo(review);
        assertThat(image.getPhotoUrl()).isEqualTo("http://example.com/image1.jpg");
    }

    @Test
    void testPhotoReviewImageUpdate() {
        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(LocalDateTime.now());
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        PhotoReviewImage image = new PhotoReviewImage(review, "http://example.com/image1.jpg");
        image.setPhotoUrl("http://example.com/image2.jpg");

        assertThat(image.getPhotoUrl()).isEqualTo("http://example.com/image2.jpg");
    }

    @Test
    void testPhotoReviewImageAllArgsConstructor() {
        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(LocalDateTime.now());
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        PhotoReviewImage image = new PhotoReviewImage(1L, review, "http://example.com/image1.jpg");

        assertThat(image.getId()).isEqualTo(1L);
        assertThat(image.getPhotoReview()).isEqualTo(review);
        assertThat(image.getPhotoUrl()).isEqualTo("http://example.com/image1.jpg");
    }

    @Test
    void testPhotoReviewImageNoArgsConstructor() {
        PhotoReviewImage image = new PhotoReviewImage();
        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(LocalDateTime.now());
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        image.setPhotoReview(review);
        image.setPhotoUrl("http://example.com/image1.jpg");

        assertThat(image.getPhotoReview()).isEqualTo(review);
        assertThat(image.getPhotoUrl()).isEqualTo("http://example.com/image1.jpg");
    }
}
