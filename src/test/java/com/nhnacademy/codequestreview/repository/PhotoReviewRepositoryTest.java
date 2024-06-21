package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.PhotoReview;
import java.util.ArrayList;
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
class PhotoReviewRepositoryTest {

    @Autowired
    private PhotoReviewRepository photoReviewRepository;

    @Test
    void testFindAll() {
        PhotoReview review = new PhotoReview(null, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 20, 1L, 1L, 1L, new ArrayList<>());
        photoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<PhotoReview> page = photoReviewRepository.findAll(pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testFindAllByClientId() {
        PhotoReview review = new PhotoReview(null, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 20, 1L, 1L, 1L, new ArrayList<>());
        photoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<PhotoReview> page = photoReviewRepository.findAllByClientId(1L, pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testFindAllByProductId() {
        PhotoReview review = new PhotoReview(null, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 20, 1L, 1L, 1L, new ArrayList<>());
        photoReviewRepository.save(review);
        Pageable pageable = PageRequest.of(0, 10);
        Page<PhotoReview> page = photoReviewRepository.findAllByProductId(1L, pageable);
        assertThat(page.getTotalElements()).isGreaterThan(0);
    }
}