package com.nhnacademy.codequestreview.entity;


import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


class NoPhotoReviewTest {

    @Test
    void testNoPhotoReviewCreation() {
        LocalDateTime now = LocalDateTime.now();

        NoPhotoReview review = new NoPhotoReview();
        review.setScore((byte) 5);
        review.setContent("Great product!");
        review.setRegisterDate(now);
        review.setPoint(10);
        review.setClientId(1L);
        review.setOrderDetailId(1L);
        review.setProductId(1L);

        assertThat(review.getScore()).isEqualTo((byte) 5);
        assertThat(review.getContent()).isEqualTo("Great product!");
        assertThat(review.getPoint()).isEqualTo(10);
        assertThat(review.getClientId()).isEqualTo(1L);
        assertThat(review.getOrderDetailId()).isEqualTo(1L);
        assertThat(review.getProductId()).isEqualTo(1L);
        assertThat(review.getRegisterDate()).isEqualTo(now);
        assertThat(review.getLastModifyDate()).isNull();
    }

    @Test
    void testNoPhotoReviewUpdate() {
        LocalDateTime now = LocalDateTime.now();

        NoPhotoReview review = new NoPhotoReview();
        review.setScore((byte) 5);
        review.setContent("Great product!");
        review.setRegisterDate(now);
        review.setPoint(10);
        review.setClientId(1L);
        review.setOrderDetailId(1L);
        review.setProductId(1L);

        LocalDateTime newTime = LocalDateTime.now();
        review.setScore((byte) 4);
        review.setContent("Updated content");
        review.setLastModifyDate(newTime);

        assertThat(review.getScore()).isEqualTo((byte) 4);
        assertThat(review.getContent()).isEqualTo("Updated content");
        assertThat(review.getLastModifyDate()).isEqualTo(newTime);
    }

    @Test
    void testNoPhotoReviewAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime modifyDate = LocalDateTime.now();

        NoPhotoReview review = new NoPhotoReview(1L, (byte) 5, "Great product!", now, modifyDate, 10, 1L, 1L, 1L);

        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getScore()).isEqualTo((byte) 5);
        assertThat(review.getContent()).isEqualTo("Great product!");
        assertThat(review.getRegisterDate()).isEqualTo(now);
        assertThat(review.getLastModifyDate()).isEqualTo(modifyDate);
        assertThat(review.getPoint()).isEqualTo(10);
        assertThat(review.getClientId()).isEqualTo(1L);
        assertThat(review.getOrderDetailId()).isEqualTo(1L);
        assertThat(review.getProductId()).isEqualTo(1L);
    }
}
