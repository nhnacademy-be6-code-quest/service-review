package com.nhnacademy.codequestreview.entity;


import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;


class PhotoReviewTest {

    @Test
    void testPhotoReviewCreation() {
        LocalDateTime now = LocalDateTime.now();

        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(now);
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        assertThat(review.getScore()).isEqualTo((byte) 5);
        assertThat(review.getContent()).isEqualTo("Amazing product!");
        assertThat(review.getRegisterDate()).isEqualTo(now);
        assertThat(review.getPoint()).isEqualTo(100);
        assertThat(review.getClientId()).isEqualTo(1L);
        assertThat(review.getOrderDetailId()).isEqualTo(2L);
        assertThat(review.getProductId()).isEqualTo(3L);
        assertThat(review.getPhotoReviewImages()).isEmpty();
    }

    @Test
    void testPhotoReviewUpdate() {
        LocalDateTime now = LocalDateTime.now();

        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(now);
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        LocalDateTime newTime = LocalDateTime.now();
        review.setScore((byte) 4);
        review.setContent("Updated content");
        review.setLastModifyDate(newTime);

        assertThat(review.getScore()).isEqualTo((byte) 4);
        assertThat(review.getContent()).isEqualTo("Updated content");
        assertThat(review.getLastModifyDate()).isEqualTo(newTime);
    }

    @Test
    void testPhotoReviewAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime modifyDate = LocalDateTime.now();
        PhotoReviewImage image1 = new PhotoReviewImage(1L, null, "http://example.com/image1.jpg");
        PhotoReviewImage image2 = new PhotoReviewImage(2L, null, "http://example.com/image2.jpg");

        PhotoReview review = new PhotoReview(1L, (byte) 5, "Amazing product!", now, modifyDate, 100, 1L, 2L, 3L, Arrays.asList(image1, image2));

        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getScore()).isEqualTo((byte) 5);
        assertThat(review.getContent()).isEqualTo("Amazing product!");
        assertThat(review.getRegisterDate()).isEqualTo(now);
        assertThat(review.getLastModifyDate()).isEqualTo(modifyDate);
        assertThat(review.getPoint()).isEqualTo(100);
        assertThat(review.getClientId()).isEqualTo(1L);
        assertThat(review.getOrderDetailId()).isEqualTo(2L);
        assertThat(review.getProductId()).isEqualTo(3L);
        assertThat(review.getPhotoReviewImages()).containsExactly(image1, image2);
    }

    @Test
    void testAddPhotoReviewImage() {
        LocalDateTime now = LocalDateTime.now();
        PhotoReview review = new PhotoReview();
        review.setScore((byte) 5);
        review.setContent("Amazing product!");
        review.setRegisterDate(now);
        review.setPoint(100);
        review.setClientId(1L);
        review.setOrderDetailId(2L);
        review.setProductId(3L);

        PhotoReviewImage image1 = new PhotoReviewImage(null, review, "http://example.com/image1.jpg");
        PhotoReviewImage image2 = new PhotoReviewImage(null, review, "http://example.com/image2.jpg");

        review.getPhotoReviewImages().add(image1);
        review.getPhotoReviewImages().add(image2);

        assertThat(review.getPhotoReviewImages()).containsExactly(image1, image2);
    }
}
