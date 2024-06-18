package com.nhnacademy.codequestreview.controller;

import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.service.PhotoReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/photo-reviews")
@RequiredArgsConstructor
public class PhotoReviewController {
    private final PhotoReviewService photoReviewService;

    @PostMapping
    public ResponseEntity<PhotoReviewResponseDTO> createReview(@Valid @RequestBody PhotoReviewRequestDTO requestDTO) {
        return new ResponseEntity<>(photoReviewService.createReview(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return photoReviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PhotoReviewResponseDTO>> getAllReviews() {
        return ResponseEntity.ok(photoReviewService.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoReviewResponseDTO> updateReview(@PathVariable Long id, @Valid @RequestBody PhotoReviewRequestDTO requestDTO) {
        return ResponseEntity.ok(photoReviewService.updateReview(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        photoReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
