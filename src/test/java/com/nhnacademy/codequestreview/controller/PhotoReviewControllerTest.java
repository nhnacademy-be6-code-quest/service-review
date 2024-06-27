package com.nhnacademy.codequestreview.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.codequestreview.dto.request.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.imp.PhotoReviewServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(PhotoReviewController.class)
class PhotoReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoReviewServiceImp photoReviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() throws Exception {
        PhotoReviewRequestDTO requestDTO = new PhotoReviewRequestDTO();
        requestDTO.setScore((byte) 5);
        requestDTO.setContent("Amazing product!");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);
        requestDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Amazing product!");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        when(photoReviewService.createReview(any(PhotoReviewRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/photo-reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Amazing product!"))
            .andDo(print());
    }

    @Test
    void testGetReviewById() throws Exception {
        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Amazing product!");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        when(photoReviewService.getReviewById(anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(get("/photo-reviews/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Amazing product!"))
            .andDo(print());
    }

    @Test
    void testGetAllReviews() throws Exception {
        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Amazing product!");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        Page<PhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);

        when(photoReviewService.getAllReviews(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/photo-reviews")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andDo(print());
    }

//    @Test
//    void testGetAllReviewsByClientId() throws Exception {
//        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
//        responseDTO.setId(1L);
//        responseDTO.setScore((byte) 5);
//        responseDTO.setContent("Amazing product!");
//        responseDTO.setRegisterDate(LocalDateTime.now());
//        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));
//
//        Page<PhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);
//
//        when(photoReviewService.getAllReviewsByClientId(anyLong(), any(Pageable.class))).thenReturn(page);
//
//        mockMvc.perform(get("/photo-reviews/client/1")
//                .param("page", "0")
//                .param("size", "10"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.content[0].id").value(1L))
//            .andDo(print());
//    }

    @Test
    void testGetAllReviewsByProductId() throws Exception {
        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Amazing product!");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image1.jpg"));

        Page<PhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);

        when(photoReviewService.getAllReviewsByProductId(anyLong(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/photo-reviews/product/1")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andDo(print());
    }

    @Test
    void testUpdateReview() throws Exception {
        PhotoReviewRequestDTO requestDTO = new PhotoReviewRequestDTO();
        requestDTO.setScore((byte) 4);
        requestDTO.setContent("Updated content");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);
        requestDTO.setPhotoUrls(Arrays.asList("http://example.com/image2.jpg"));

        PhotoReviewResponseDTO responseDTO = new PhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 4);
        responseDTO.setContent("Updated content");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setLastModifyDate(LocalDateTime.now());
        responseDTO.setPhotoUrls(Arrays.asList("http://example.com/image2.jpg"));

        when(photoReviewService.updateReview(anyLong(), any(PhotoReviewRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/photo-reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Updated content"))
            .andDo(print());
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(photoReviewService).deleteReview(anyLong());

        mockMvc.perform(delete("/photo-reviews/1"))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

    @Test
    void testDeleteReview_NotFound() throws Exception {
        doThrow(new ReviewNotFoundException("리뷰를 찾을 수 없습니다. ID : 1")).when(photoReviewService).deleteReview(anyLong());

        mockMvc.perform(delete("/photo-reviews/1"))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
}