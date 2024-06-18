package com.nhnacademy.codequestreview.controller;

import com.nhnacademy.codequestreview.dto.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.service.NoPhotoReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/no-photo-reviews")
@RequiredArgsConstructor
public class NoPhotoReviewController {
    private final NoPhotoReviewService noPhotoReviewService;

    @PostMapping
    public ResponseEntity<NoPhotoReviewResponseDTO> createReview(@Valid @RequestBody NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        return new ResponseEntity<>(noPhotoReviewService.createReview(noPhotoReviewRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoPhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return noPhotoReviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<NoPhotoReviewResponseDTO>> getAllReviews() {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoPhotoReviewResponseDTO> updateReview(@PathVariable Long id, @RequestBody NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        return ResponseEntity.ok(noPhotoReviewService.updateReview(id, noPhotoReviewRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        noPhotoReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
