package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.PhotoReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotoReviewRepository extends JpaRepository<PhotoReview, Long> {

    Page<PhotoReview> findAll(Pageable pageable);

    Page<PhotoReview> findAllByClientId(Long clientId, Pageable pageable);

    Page<PhotoReview> findAllByProductId(Long productId, Pageable pageable);
}
