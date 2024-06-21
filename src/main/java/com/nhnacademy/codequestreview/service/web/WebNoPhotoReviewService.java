package com.nhnacademy.codequestreview.service.web;


import com.nhnacademy.codequestreview.client.NoPhotoReviewClient;
import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WebNoPhotoReviewService {

    private final NoPhotoReviewClient noPhotoReviewClient;


    public ResponseEntity<NoPhotoReviewResponseDTO> createReview(
        NoPhotoReviewRequestDTO requestDTO) {
        return noPhotoReviewClient.createReview(requestDTO);
    }

    public ResponseEntity<NoPhotoReviewResponseDTO> getReviewById(Long id) {
        return noPhotoReviewClient.getReviewById(id);
    }

    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return noPhotoReviewClient.getAllReviews(pageable);
    }

    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByClientId(Long clientId,
        Pageable pageable) {
        return noPhotoReviewClient.getAllReviewsByClientId(clientId, pageable);
    }

    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByProductId(Long productId,
        Pageable pageable) {
        return noPhotoReviewClient.getAllReviewsByProductId(productId, pageable);
    }

    public ResponseEntity<NoPhotoReviewResponseDTO> updateReview(Long id,
        NoPhotoReviewRequestDTO requestDTO) {
        return noPhotoReviewClient.updateReview(id, requestDTO);
    }

    public ResponseEntity<Void> deleteReview(Long id) {
        return noPhotoReviewClient.deleteReview(id);
    }

}
