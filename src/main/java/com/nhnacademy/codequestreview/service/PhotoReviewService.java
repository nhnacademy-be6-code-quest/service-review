package com.nhnacademy.codequestreview.service;


import com.nhnacademy.codequestreview.dto.request.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.PhotoReviewResponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PhotoReviewService {

    PhotoReviewResponseDTO createReview(PhotoReviewRequestDTO requestDTO);

    Optional<PhotoReviewResponseDTO> getReviewById(Long id);

    Page<PhotoReviewResponseDTO> getAllReviews(Pageable pageable);

    Page<PhotoReviewResponseDTO> getAllReviewsByClientId(Long clientId, Pageable pageable);

    Page<PhotoReviewResponseDTO> getAllReviewsByProductId(Long productId, Pageable pageable);

    PhotoReviewResponseDTO updateReview(Long id, PhotoReviewRequestDTO requestDTO);

    void deleteReview(Long id);

    boolean isReviewExist(Long id);
}
