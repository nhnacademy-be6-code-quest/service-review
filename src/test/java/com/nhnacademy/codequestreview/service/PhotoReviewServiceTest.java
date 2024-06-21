package com.nhnacademy.codequestreview.service;


import com.nhnacademy.codequestreview.dto.request.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.entity.PhotoReview;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.repository.PhotoReviewRepository;
import com.nhnacademy.codequestreview.repository.PhotoReviewImageRepository;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PhotoReviewServiceTest {

    @Mock
    private PhotoReviewRepository photoReviewRepository;

    @Mock
    private PhotoReviewImageRepository photoReviewImageRepository;

    @InjectMocks
    private PhotoReviewService photoReviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() {
        PhotoReviewRequestDTO requestDTO = new PhotoReviewRequestDTO();
        requestDTO.setScore((byte) 5);
        requestDTO.setContent("Amazing product!");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);
        requestDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        PhotoReview review = new PhotoReview(null, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 500, 1L, 1L, 1L, Collections.emptyList());
        when(photoReviewRepository.save(any(PhotoReview.class))).thenReturn(review);

        PhotoReviewResponseDTO responseDTO = photoReviewService.createReview(requestDTO);

        assertThat(responseDTO.getContent()).isEqualTo("Amazing product!");
    }

    @Test
    void testGetReviewById() {
        PhotoReview review = new PhotoReview(1L, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 500, 1L, 1L, 1L, Collections.emptyList());
        when(photoReviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Optional<PhotoReviewResponseDTO> responseDTO = photoReviewService.getReviewById(1L);

        assertThat(responseDTO.isPresent()).isTrue();
        assertThat(responseDTO.get().getContent()).isEqualTo("Amazing product!");
    }

    @Test
    void testGetAllReviews() {
        PhotoReview review = new PhotoReview(1L, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 500, 1L, 1L, 1L, Collections.emptyList());
        Page<PhotoReview> page = new PageImpl<>(Collections.singletonList(review));
        when(photoReviewRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<PhotoReviewResponseDTO> responseDTOPage = photoReviewService.getAllReviews(PageRequest.of(0, 10));

        assertThat(responseDTOPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void testUpdateReview() {
        PhotoReview review = new PhotoReview(1L, (byte) 5, "Amazing product!", LocalDateTime.now(), null, 500, 1L, 1L, 1L, Collections.emptyList());
        when(photoReviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(photoReviewRepository.save(any(PhotoReview.class))).thenReturn(review);

        PhotoReviewRequestDTO requestDTO = new PhotoReviewRequestDTO();
        requestDTO.setScore((byte) 4);
        requestDTO.setContent("Updated content");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);
        requestDTO.setPhotoUrls(Arrays.asList("http://example.com/image2.jpg"));

        PhotoReviewResponseDTO responseDTO = photoReviewService.updateReview(1L, requestDTO);

        assertThat(responseDTO.getContent()).isEqualTo("Updated content");
        assertThat(responseDTO.getScore()).isEqualTo((byte) 4);
    }

    @Test
    void testDeleteReview() {
        when(photoReviewRepository.existsById(1L)).thenReturn(true);
        doNothing().when(photoReviewRepository).deleteById(1L);

        photoReviewService.deleteReview(1L);

        verify(photoReviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReview_NotFound() {
        when(photoReviewRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> photoReviewService.deleteReview(1L))
            .isInstanceOf(ReviewNotFoundException.class)
            .hasMessageContaining("리뷰를 찾을 수 없습니다. ID : 1");
    }
}