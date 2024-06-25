package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.request.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.imp.PhotoReviewServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "PhotoReview API", description = "PhotoReview API 입니다.")
public class PhotoReviewController {

    private final PhotoReviewServiceImp photoReviewService;

    @PostMapping
    @Operation(summary = "사진있는 리뷰생성", description = "사진있는 리뷰를 생성합니다.")
    public ResponseEntity<PhotoReviewResponseDTO> createReview(
        @Valid @RequestBody PhotoReviewRequestDTO requestDTO) {
        return new ResponseEntity<>(photoReviewService.createReview(requestDTO),
            HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "리뷰아이디로 리뷰조회", description = "리뷰아이디로 리뷰를 조회합니다.")
    public ResponseEntity<PhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return photoReviewService.getReviewById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "모든 리뷰조회", description = "모든리뷰를 조회합니다.")
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviews(pageable));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "유저아이디로 리뷰조회", description = "유저아이디로 리뷰를 조회합니다.")
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviewsByClientId(
        @PathVariable Long clientId, Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviewsByClientId(clientId, pageable));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "상품아이디로 리뷰조회", description = "상품아이디로 리뷰를 조회합니다.")
    public ResponseEntity<Page<PhotoReviewResponseDTO>> getAllReviewsByProductId(
        @PathVariable Long productId, Pageable pageable) {
        return ResponseEntity.ok(photoReviewService.getAllReviewsByProductId(productId, pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "사진있는 리뷰수정", description = "사진있는 리뷰를 수정합니다.")
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
    @Operation(summary = "사진있는 리뷰삭제", description = "사진있는 리뷰를 삭제합니다.")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            photoReviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/has-written/{orderDetailId}")
    @Operation(summary = "리뷰작성여부 조회", description = "리뷰작성여부를 조회합니다.")
    public ResponseEntity<Boolean> hasWrittenReview(@PathVariable Long orderDetailId) {
        return ResponseEntity.ok(photoReviewService.isReviewExist(orderDetailId));
    }

}
