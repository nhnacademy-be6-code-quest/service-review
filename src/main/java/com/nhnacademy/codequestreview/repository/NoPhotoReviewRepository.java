package com.nhnacademy.codequestreview.repository;


import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoPhotoReviewRepository extends JpaRepository<NoPhotoReview, Long> {
    Page<NoPhotoReview> findAll(Pageable pageable);

    Page<NoPhotoReview> findAllByClientId(Long clientId, Pageable pageable);

    Page<NoPhotoReview> findAllByProductId(Long productId, Pageable pageable);

    boolean existsByOrderDetailId(Long orderDetailId);
}
