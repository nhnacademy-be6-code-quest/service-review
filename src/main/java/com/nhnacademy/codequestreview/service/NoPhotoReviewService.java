package com.nhnacademy.codequestreview.service;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NoPhotoReviewService {

    NoPhotoReviewResponseDTO createReview(NoPhotoReviewRequestDTO noPhotoReviewRequestDTO);

    Optional<NoPhotoReviewResponseDTO> getReviewById(Long id);

    Page<NoPhotoReviewResponseDTO> getAllReviews(Pageable pageable);

    Page<NoPhotoReviewResponseDTO> getAllReviewsByClientId(Long clientId, Pageable pageable);

    Page<NoPhotoReviewResponseDTO> getAllReviewsByProductId(Long productId, Pageable pageable);

    NoPhotoReviewResponseDTO updateReview(Long id, NoPhotoReviewRequestDTO noPhotoReviewRequestDTO);

    void deleteReview(Long id);

    boolean isReviewExist(Long orderDetailId);
}
