package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.NoPhotoReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/no-photo-reviews")
@RequiredArgsConstructor
public class NoPhotoReviewController {

    private final NoPhotoReviewService noPhotoReviewService;

    @PostMapping
    public ResponseEntity<NoPhotoReviewResponseDTO> createReview(
        @Valid @RequestBody NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        return new ResponseEntity<>(noPhotoReviewService.createReview(noPhotoReviewRequestDTO),
            HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoPhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return noPhotoReviewService.getReviewById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviews(pageable));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByClientId(
        @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviewsByClientId(id, pageable));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByProductId(
        @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviewsByProductId(id, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoPhotoReviewResponseDTO> updateReview(@PathVariable Long id,
        @Valid @RequestBody NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        try {
            NoPhotoReviewResponseDTO updatedReview = noPhotoReviewService.updateReview(id,
                noPhotoReviewRequestDTO);
            return ResponseEntity.ok(updatedReview);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            noPhotoReviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
