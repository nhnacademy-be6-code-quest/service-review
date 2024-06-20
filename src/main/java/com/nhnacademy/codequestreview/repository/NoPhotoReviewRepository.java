package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoPhotoReviewRepository extends JpaRepository<NoPhotoReview, Long> {
    Page<NoPhotoReview> findAll(Pageable pageable);
}
