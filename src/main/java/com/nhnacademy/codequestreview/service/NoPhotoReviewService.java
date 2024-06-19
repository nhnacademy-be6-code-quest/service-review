package com.nhnacademy.codequestreview.service;


import com.nhnacademy.codequestreview.dto.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import com.nhnacademy.codequestreview.repository.NoPhotoReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class NoPhotoReviewService {
    private static final int DEFAULT_POINT = 200;
    private final NoPhotoReviewRepository noPhotoReviewRepository;

    @Transactional
    public NoPhotoReviewResponseDTO createReview(NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        NoPhotoReview noPhotoReview = toEntity(noPhotoReviewRequestDTO);
        NoPhotoReview savedReview = noPhotoReviewRepository.save(noPhotoReview);
        return toResponseDTO(savedReview);
    }

    public Optional<NoPhotoReviewResponseDTO> getReviewById(Long id) {
        return noPhotoReviewRepository.findById(id).map(this::toResponseDTO);
    }

    public List<NoPhotoReviewResponseDTO> getAllReviews() {
        return noPhotoReviewRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public NoPhotoReviewResponseDTO updateReview(Long id, NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        NoPhotoReview noPhotoReview = noPhotoReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        noPhotoReview.setScore(noPhotoReviewRequestDTO.getScore());
        noPhotoReview.setContent(noPhotoReviewRequestDTO.getContent());
        noPhotoReview.setLastModifyDate(LocalDateTime.now());
        noPhotoReview.setClientId(noPhotoReviewRequestDTO.getClientId());
        noPhotoReview.setOrderDetailId(noPhotoReviewRequestDTO.getOrderDetailId());

        NoPhotoReview updatedReview = noPhotoReviewRepository.save(noPhotoReview);
        return toResponseDTO(updatedReview);
    }

    @Transactional
    public void deleteReview(Long id) {
        noPhotoReviewRepository.deleteById(id);
    }

    private NoPhotoReview toEntity(NoPhotoReviewRequestDTO dto) {
        NoPhotoReview noPhotoReview = new NoPhotoReview();
        noPhotoReview.setScore(dto.getScore());
        noPhotoReview.setContent(dto.getContent());
        noPhotoReview.setRegisterDate(LocalDateTime.now());
        noPhotoReview.setPoint(DEFAULT_POINT);
        noPhotoReview.setClientId(dto.getClientId());
        noPhotoReview.setOrderDetailId(dto.getOrderDetailId());
        return noPhotoReview;
    }

    private NoPhotoReviewResponseDTO toResponseDTO(NoPhotoReview noPhotoReview) {
        NoPhotoReviewResponseDTO dto = new NoPhotoReviewResponseDTO();
        dto.setId(noPhotoReview.getId());
        dto.setScore(noPhotoReview.getScore());
        dto.setContent(noPhotoReview.getContent());
        dto.setRegisterDate(noPhotoReview.getRegisterDate());
        dto.setLastModifyDate(noPhotoReview.getLastModifyDate());
        dto.setPoint(noPhotoReview.getPoint());
        dto.setClientId(noPhotoReview.getClientId());
        dto.setOrderDetailId(noPhotoReview.getOrderDetailId());
        return dto;
    }
}
