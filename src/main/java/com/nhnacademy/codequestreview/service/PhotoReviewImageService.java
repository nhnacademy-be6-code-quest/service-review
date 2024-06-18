package com.nhnacademy.codequestreview.service;

import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import com.nhnacademy.codequestreview.repository.PhotoReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PhotoReviewImageService {
    private final PhotoReviewImageRepository photoReviewImageRepository;

    public PhotoReviewImage savePhotoReviewImage(PhotoReviewImage photoReviewImage) {
        return photoReviewImageRepository.save(photoReviewImage);
    }

    public List<PhotoReviewImage> getAllPhotoReviewImages() {
        return photoReviewImageRepository.findAll();
    }

    public Optional<PhotoReviewImage> getPhotoReviewImageById(Long id) {
        return photoReviewImageRepository.findById(id);
    }

    public void deletePhotoReviewImage(Long id) {
        photoReviewImageRepository.deleteById(id);
    }

}
