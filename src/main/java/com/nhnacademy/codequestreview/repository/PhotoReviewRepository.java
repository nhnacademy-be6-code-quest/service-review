package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.PhotoReview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotoReviewRepository extends JpaRepository<PhotoReview, Long> {
}
