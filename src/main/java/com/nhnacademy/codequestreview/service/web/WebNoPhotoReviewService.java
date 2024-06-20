package com.nhnacademy.codequestreview.service.web;


import com.nhnacademy.codequestreview.client.NoPhotoReviewClient;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class WebNoPhotoReviewService {

    private final NoPhotoReviewClient noPhotoReviewClient;


    public ResponseEntity<NoPhotoReviewResponseDTO> createReview(NoPhotoReviewRequestDTO requestDTO) {
        return noPhotoReviewClient.createReview(requestDTO);
    }

    public ResponseEntity<NoPhotoReviewResponseDTO> getReviewById(Long id) {
        return noPhotoReviewClient.getReviewById(id);
    }

//    public ResponseEntity<List<NoPhotoReviewResponseDTO>> getAllReviews() {
//        return noPhotoReviewClient.getAllReviews();
//    }

    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return noPhotoReviewClient.getAllReviews(pageable);
    }

    public ResponseEntity<NoPhotoReviewResponseDTO> updateReview(Long id, NoPhotoReviewRequestDTO requestDTO) {
        return noPhotoReviewClient.updateReview(id, requestDTO);
    }

    public ResponseEntity<Void> deleteReview(Long id) {
        return noPhotoReviewClient.deleteReview(id);
    }

}
