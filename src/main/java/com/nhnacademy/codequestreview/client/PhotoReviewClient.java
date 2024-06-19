package com.nhnacademy.codequestreview.client;

import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "photoReviewClient", url = "http://localhost:8080/photo-reviews")
public interface PhotoReviewClient {

    @PostMapping
    ResponseEntity<PhotoReviewResponseDTO> createReview(
        @RequestBody PhotoReviewRequestDTO requestDTO);

    @GetMapping("/{id}")
    ResponseEntity<PhotoReviewResponseDTO> getReviewById(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<PhotoReviewResponseDTO>> getAllReviews();

    @PutMapping("/{id}")
    ResponseEntity<PhotoReviewResponseDTO> updateReview(@PathVariable("id") Long id,
        @RequestBody PhotoReviewRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteReview(@PathVariable("id") Long id);

}