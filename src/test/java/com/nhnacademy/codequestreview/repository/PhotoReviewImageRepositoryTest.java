package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.PhotoReview;
import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PhotoReviewImageRepositoryTest {

    @Autowired
    private PhotoReviewImageRepository photoReviewImageRepository;

    @Autowired
    private PhotoReviewRepository photoReviewRepository;

    @Test
    void testSaveAndFindPhotoReviewImage() {
        PhotoReview review = new PhotoReview(null, (byte) 4, "Good product!", LocalDateTime.now(), null, 15, 1L, 1L, 1L, new ArrayList<>());
        photoReviewRepository.save(review);

        PhotoReviewImage image = new PhotoReviewImage(review, "http://example.com/image1.jpg");
        photoReviewImageRepository.save(image);

        PhotoReviewImage found = photoReviewImageRepository.findById(image.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getPhotoUrl()).isEqualTo("http://example.com/image1.jpg");
    }
}