package com.nhnacademy.codequestreview.service.imp;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.repository.NoPhotoReviewRepository;
import com.nhnacademy.codequestreview.service.NoPhotoReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class NoPhotoReviewServiceImp implements NoPhotoReviewService {

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

    public Page<NoPhotoReviewResponseDTO> getAllReviews(Pageable pageable) {
        return noPhotoReviewRepository.findAll(pageable)
            .map(this::toResponseDTO);
    }


    public Page<NoPhotoReviewResponseDTO> getAllReviewsByClientId(Long clientId,
        Pageable pageable) {
        return noPhotoReviewRepository.findAllByClientId(clientId, pageable)
            .map(this::toResponseDTO);
    }

    public Page<NoPhotoReviewResponseDTO> getAllReviewsByProductId(Long productId,
        Pageable pageable) {
        return noPhotoReviewRepository.findAllByProductId(productId, pageable)
            .map(this::toResponseDTO);
    }

    @Transactional
    public NoPhotoReviewResponseDTO updateReview(Long id,
        NoPhotoReviewRequestDTO noPhotoReviewRequestDTO) {
        NoPhotoReview noPhotoReview = noPhotoReviewRepository.findById(id)
            .orElseThrow(() -> new ReviewNotFoundException("리뷰를 찾을 수 없습니다. ID: " + id));

        noPhotoReview.setScore(noPhotoReviewRequestDTO.getScore());
        noPhotoReview.setContent(noPhotoReviewRequestDTO.getContent());
        noPhotoReview.setLastModifyDate(LocalDateTime.now());

        NoPhotoReview updatedReview = noPhotoReviewRepository.save(noPhotoReview);
        return toResponseDTO(updatedReview);
    }

    @Transactional
    public void deleteReview(Long id) {
        if (!noPhotoReviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("리뷰를 찾을 수 없습니다. ID : " + id);
        }
        noPhotoReviewRepository.deleteById(id);
    }

    @Override
    public boolean isReviewExist(Long orderDetailId) {
        return noPhotoReviewRepository.existsByOrderDetailId(orderDetailId);
    }

    private NoPhotoReview toEntity(NoPhotoReviewRequestDTO dto) {
        NoPhotoReview noPhotoReview = new NoPhotoReview();
        noPhotoReview.setScore(dto.getScore());
        noPhotoReview.setContent(dto.getContent());
        noPhotoReview.setRegisterDate(LocalDateTime.now());
        noPhotoReview.setPoint(DEFAULT_POINT);
        noPhotoReview.setClientId(dto.getClientId());
        noPhotoReview.setOrderDetailId(dto.getOrderDetailId());
        noPhotoReview.setProductId(dto.getProductId());
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
        dto.setProductId(noPhotoReview.getProductId());
        return dto;
    }

}
