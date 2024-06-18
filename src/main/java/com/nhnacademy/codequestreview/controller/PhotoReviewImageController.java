package com.nhnacademy.codequestreview.controller;

import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import com.nhnacademy.codequestreview.service.PhotoReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/photo-review-images")
public class PhotoReviewImageController {
    private final PhotoReviewImageService photoReviewImageService;

    @PostMapping
    public ResponseEntity<PhotoReviewImage> createPhotoReviewImage(@RequestBody PhotoReviewImage photoReviewImage) {
        PhotoReviewImage createdPhotoReviewImage = photoReviewImageService.savePhotoReviewImage(photoReviewImage);
        return ResponseEntity.ok(createdPhotoReviewImage);
    }

    @GetMapping
    public ResponseEntity<List<PhotoReviewImage>> getAllPhotoReviewImages() {
        List<PhotoReviewImage> photoReviewImages = photoReviewImageService.getAllPhotoReviewImages();
        return ResponseEntity.ok(photoReviewImages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoReviewImage> getPhotoReviewImageById(@PathVariable Long id) {
        Optional<PhotoReviewImage> photoReviewImage = photoReviewImageService.getPhotoReviewImageById(id);
        return photoReviewImage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoReviewImage(@PathVariable Long id) {
        photoReviewImageService.deletePhotoReviewImage(id);
        return ResponseEntity.noContent().build();
    }

}
