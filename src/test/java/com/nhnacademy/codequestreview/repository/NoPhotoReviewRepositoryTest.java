package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoPhotoReviewRepositoryTest {

    @Autowired
    private NoPhotoReviewRepository noPhotoReviewRepository;

    @Test
    void testFindAll() {
        NoPhotoReview review = new NoPhotoReview(null, (byte) 5, "Great product!", LocalDateTime.now(), null, 10, 1L, 1L, 1L);
        noPhotoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoPhotoReview> page = noPhotoReviewRepository.findAll(pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testFindAllByClientId() {
        NoPhotoReview review = new NoPhotoReview(null, (byte) 5, "Great product!", LocalDateTime.now(), null, 10, 1L, 1L, 1L);
        noPhotoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoPhotoReview> page = noPhotoReviewRepository.findAllByClientId(1L, pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testFindAllByProductId() {
        NoPhotoReview review = new NoPhotoReview(null, (byte) 5, "Great product!", LocalDateTime.now(), null, 10, 1L, 1L, 1L);
        noPhotoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoPhotoReview> page = noPhotoReviewRepository.findAllByProductId(1L, pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }
}