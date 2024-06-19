package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotoReviewImageRepository extends JpaRepository<PhotoReviewImage, Long> {
    void deleteByPhotoReviewId(Long photoReviewId);
}
