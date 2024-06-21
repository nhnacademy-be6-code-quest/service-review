package com.nhnacademy.codequestreview.service;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.entity.NoPhotoReview;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.repository.NoPhotoReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NoPhotoReviewServiceTest {

    @Mock
    private NoPhotoReviewRepository noPhotoReviewRepository;

    @InjectMocks
    private NoPhotoReviewService noPhotoReviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() {
        NoPhotoReviewRequestDTO requestDTO = new NoPhotoReviewRequestDTO();
        requestDTO.setScore((byte) 5);
        requestDTO.setContent("Great product!");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);

        NoPhotoReview review = new NoPhotoReview(null, (byte) 5, "Great product!", LocalDateTime.now(), null, 200, 1L, 1L, 1L);
        when(noPhotoReviewRepository.save(any(NoPhotoReview.class))).thenReturn(review);

        NoPhotoReviewResponseDTO responseDTO = noPhotoReviewService.createReview(requestDTO);

        assertThat(responseDTO.getContent()).isEqualTo("Great product!");
    }

    @Test
    void testGetReviewById() {
        NoPhotoReview review = new NoPhotoReview(1L, (byte) 5, "Great product!", LocalDateTime.now(), null, 200, 1L, 1L, 1L);
        when(noPhotoReviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Optional<NoPhotoReviewResponseDTO> responseDTO = noPhotoReviewService.getReviewById(1L);

        assertThat(responseDTO.isPresent()).isTrue();
        assertThat(responseDTO.get().getContent()).isEqualTo("Great product!");
    }

    @Test
    void testGetAllReviews() {
        NoPhotoReview review = new NoPhotoReview(1L, (byte) 5, "Great product!", LocalDateTime.now(), null, 200, 1L, 1L, 1L);
        Page<NoPhotoReview> page = new PageImpl<>(Collections.singletonList(review));
        when(noPhotoReviewRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<NoPhotoReviewResponseDTO> responseDTOPage = noPhotoReviewService.getAllReviews(PageRequest.of(0, 10));

        assertThat(responseDTOPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testUpdateReview() {
        NoPhotoReview review = new NoPhotoReview(1L, (byte) 5, "Great product!", LocalDateTime.now(), null, 200, 1L, 1L, 1L);
        when(noPhotoReviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(noPhotoReviewRepository.save(any(NoPhotoReview.class))).thenReturn(review);

        NoPhotoReviewRequestDTO requestDTO = new NoPhotoReviewRequestDTO();
        requestDTO.setScore((byte) 4);
        requestDTO.setContent("Updated content");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);

        NoPhotoReviewResponseDTO responseDTO = noPhotoReviewService.updateReview(1L, requestDTO);

        assertThat(responseDTO.getContent()).isEqualTo("Updated content");
        assertThat(responseDTO.getScore()).isEqualTo((byte) 4);
    }

    @Test
    void testDeleteReview() {
        when(noPhotoReviewRepository.existsById(1L)).thenReturn(true);
        doNothing().when(noPhotoReviewRepository).deleteById(1L);

        noPhotoReviewService.deleteReview(1L);

        verify(noPhotoReviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReview_NotFound() {
        when(noPhotoReviewRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> noPhotoReviewService.deleteReview(1L))
            .isInstanceOf(ReviewNotFoundException.class)
            .hasMessageContaining("리뷰를 찾을 수 없습니다. ID : 1");
    }
}