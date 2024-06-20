package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.PhotoReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/photo-reviews")
@RequiredArgsConstructor
public class PhotoReviewController {

    private final PhotoReviewService photoReviewService;

    @PostMapping
    public ResponseEntity<PhotoReviewResponseDTO> createReview(
        @Valid @RequestBody PhotoReviewRequestDTO requestDTO) {
        return new ResponseEntity<>(photoReviewService.createReview(requestDTO),
            HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return photoReviewService.getReviewById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviews(pageable));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviewsByClientId(
        @PathVariable Long clientId, Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviewsByClientId(clientId, pageable));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviewsByProductId(
        @PathVariable Long productId, Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviewsByProductId(productId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoReviewResponseDTO> updateReview(@PathVariable Long id,
        @Valid @RequestBody PhotoReviewRequestDTO requestDTO) {
        try {
            PhotoReviewResponseDTO updatedReview = photoReviewService.updateReview(id, requestDTO);
            return ResponseEntity.ok(updatedReview);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            photoReviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
