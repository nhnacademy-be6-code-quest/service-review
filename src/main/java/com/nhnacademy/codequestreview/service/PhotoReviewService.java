package com.nhnacademy.codequestreview.service;

import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.entity.PhotoReview;
import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import com.nhnacademy.codequestreview.repository.PhotoReviewRepository;
import com.nhnacademy.codequestreview.repository.PhotoReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhotoReviewService {
    private final int DEFAULT_POINT = 500;
    private final PhotoReviewRepository photoReviewRepository;
    private final PhotoReviewImageRepository photoReviewImageRepository;

    @Transactional
    public PhotoReviewResponseDTO createReview(PhotoReviewRequestDTO requestDTO) {
        PhotoReview photoReview = toEntity(requestDTO);
        PhotoReview savedReview = photoReviewRepository.save(photoReview);
        return toResponseDTO(savedReview);
    }

    public Optional<PhotoReviewResponseDTO> getReviewById(Long id) {
        return photoReviewRepository.findById(id).map(this::toResponseDTO);
    }

    public List<PhotoReviewResponseDTO> getAllReviews() {
        return photoReviewRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PhotoReviewResponseDTO updateReview(Long id, PhotoReviewRequestDTO requestDTO) {
        PhotoReview photoReview = photoReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        photoReview.setScore(requestDTO.getScore());
        photoReview.setContent(requestDTO.getContent());
        photoReview.setLastModifyDate(LocalDateTime.now());
        photoReview.setClientId(requestDTO.getClientId());
        photoReview.setOrderDetailId(requestDTO.getOrderDetailId());

        List<PhotoReviewImage> newImages = requestDTO.getPhotoUrls().stream()
                .map(url -> new PhotoReviewImage(photoReview, url))
                .collect(Collectors.toList());

        photoReviewImageRepository.deleteAll(photoReview.getPhotoReviewImages());
        photoReviewImageRepository.saveAll(newImages);
        photoReview.setPhotoReviewImages(newImages);

        PhotoReview updatedReview = photoReviewRepository.save(photoReview);
        return toResponseDTO(updatedReview);
    }

    @Transactional
    public void deleteReview(Long id) {
        photoReviewRepository.deleteById(id);
    }

    private PhotoReview toEntity(PhotoReviewRequestDTO dto) {
        PhotoReview photoReview = new PhotoReview();
        photoReview.setScore(dto.getScore());
        photoReview.setContent(dto.getContent());
        photoReview.setRegisterDate(LocalDateTime.now());
        photoReview.setPoint(DEFAULT_POINT);
        photoReview.setClientId(dto.getClientId());
        photoReview.setOrderDetailId(dto.getOrderDetailId());

        List<PhotoReviewImage> images = dto.getPhotoUrls().stream()
                .map(url -> new PhotoReviewImage(photoReview, url))
                .collect(Collectors.toList());

        photoReview.setPhotoReviewImages(images);

        return photoReview;
    }

    private PhotoReviewResponseDTO toResponseDTO(PhotoReview photoReview) {
        PhotoReviewResponseDTO dto = new PhotoReviewResponseDTO();
        dto.setId(photoReview.getId());
        dto.setScore(photoReview.getScore());
        dto.setContent(photoReview.getContent());
        dto.setRegisterDate(photoReview.getRegisterDate());
        dto.setLastModifyDate(photoReview.getLastModifyDate());
        dto.setPoint(photoReview.getPoint());
        dto.setClientId(photoReview.getClientId());
        dto.setOrderDetailId(photoReview.getOrderDetailId());
        dto.setPhotoUrls(photoReview.getPhotoReviewImages().stream()
                .map(PhotoReviewImage::getPhotoUrl)
                .collect(Collectors.toList()));
        return dto;
    }
}
