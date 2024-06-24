package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.imp.NoPhotoReviewServiceImp;
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
@RequestMapping("/no-photo-reviews")
@RequiredArgsConstructor
@Tag(name = "NoPhotoReview API", description = "NoPhotoReview API 입니다.")
public class NoPhotoReviewController {

    private final NoPhotoReviewServiceImp noPhotoReviewService;

    @PostMapping
    @Operation(summary = "사진없는 리뷰생성", description = "사진없는 리뷰를 생성합니다.")
    public ResponseEntity<NoPhotoReviewResponseDTO> createReview(
        @Valid @RequestBody NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        return new ResponseEntity<>(noPhotoReviewService.createReview(noPhotoReviewRequestDTO),
            HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "리뷰아이디로 리뷰조회", description = "리뷰아아디로 리뷰를 조회합니다.")
    public ResponseEntity<NoPhotoReviewResponseDTO> getReviewById(@PathVariable Long id) {
        return noPhotoReviewService.getReviewById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "모든 리뷰조회", description = "모든리뷰를 조회합니다.")
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviews(Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviews(pageable));
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "유저아이디로 리뷰조회", description = "유저아이디로 리뷰를 조회합니다.")
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByClientId(
        @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviewsByClientId(id, pageable));
    }

    @GetMapping("/product/{id}")
    @Operation(summary = "상품아이디로 리뷰조회", description = "상품아이디로 리뷰를 조회합니다.")
    public ResponseEntity<Page<NoPhotoReviewResponseDTO>> getAllReviewsByProductId(
        @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(noPhotoReviewService.getAllReviewsByProductId(id, pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "사진없는 리뷰수정", description = "사진없는 리뷰를 수정합니다.")
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
    @Operation(summary = "사진없는 리뷰삭제", description = "사진없는 리뷰를 삭제합니다.")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            noPhotoReviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/has-written/{orderDetailId}")
    public ResponseEntity<Boolean> hasWrittenReview(@PathVariable Long orderDetailId) {
        return ResponseEntity.ok(noPhotoReviewService.isReviewExist(orderDetailId));
    }

}
