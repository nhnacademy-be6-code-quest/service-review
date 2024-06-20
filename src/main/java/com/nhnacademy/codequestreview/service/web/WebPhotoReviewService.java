package com.nhnacademy.codequestreview.service.web;

import com.nhnacademy.codequestreview.client.PhotoReviewClient;
import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WebPhotoReviewService {

    private final PhotoReviewClient photoReviewClient;

    public ResponseEntity<PhotoReviewResponseDTO> createReview(PhotoReviewRequestDTO requestDTO){
        return photoReviewClient.createReview(requestDTO);
    }

    public ResponseEntity<PhotoReviewResponseDTO> getReviewById(Long id){
        return photoReviewClient.getReviewById(id);
    }

    public ResponseEntity<List<PhotoReviewResponseDTO>> getAllReviews(){
        return photoReviewClient.getAllReviews();
    }

    public ResponseEntity<PhotoReviewResponseDTO> updateReview(Long id, PhotoReviewRequestDTO requestDTO){
        return photoReviewClient.updateReview(id, requestDTO);
    }

    public ResponseEntity<Void> deleteReview(Long id){
        return photoReviewClient.deleteReview(id);
    }

}
