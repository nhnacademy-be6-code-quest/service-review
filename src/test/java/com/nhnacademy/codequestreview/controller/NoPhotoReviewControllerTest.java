package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.exception.ReviewNotFoundException;
import com.nhnacademy.codequestreview.service.imp.NoPhotoReviewServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
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
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(NoPhotoReviewController.class)
class NoPhotoReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoPhotoReviewServiceImp noPhotoReviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() throws Exception {
        NoPhotoReviewRequestDTO requestDTO = new NoPhotoReviewRequestDTO();
        requestDTO.setScore((byte) 5);
        requestDTO.setContent("Great product!");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);

        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Great product!");
        responseDTO.setRegisterDate(LocalDateTime.now());

        when(noPhotoReviewService.createReview(any(NoPhotoReviewRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/no-photo-reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Great product!"))
            .andDo(print());
    }

    @Test
    void testGetReviewById() throws Exception {
        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Great product!");
        responseDTO.setRegisterDate(LocalDateTime.now());

        when(noPhotoReviewService.getReviewById(anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(get("/no-photo-reviews/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Great product!"))
            .andDo(print());
    }

    @Test
    void testGetAllReviews() throws Exception {
        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Great product!");
        responseDTO.setRegisterDate(LocalDateTime.now());

        Page<NoPhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);

        when(noPhotoReviewService.getAllReviews(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/no-photo-reviews")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andDo(print());
    }

    // TODO 1 fix testcode
//    @Test
//    void testGetAllReviewsByClientId() throws Exception {
//        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
//        responseDTO.setId(1L);
//        responseDTO.setScore((byte) 5);
//        responseDTO.setContent("Great product!");
//        responseDTO.setRegisterDate(LocalDateTime.now());
//
//        Page<NoPhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);
//
//        when(noPhotoReviewService.getAllReviewsByClientId(anyLong(), any(Pageable.class))).thenReturn(page);
//
//        mockMvc.perform(get("/no-photo-reviews/client/1")
//                .param("page", "0")
//                .param("size", "10"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.content[0].id").value(1L))
//            .andDo(print());
//    }

    @Test
    void testGetAllReviewsByProductId() throws Exception {
        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 5);
        responseDTO.setContent("Great product!");
        responseDTO.setRegisterDate(LocalDateTime.now());

        Page<NoPhotoReviewResponseDTO> page = new PageImpl<>(Collections.singletonList(responseDTO), PageRequest.of(0, 10), 1);

        when(noPhotoReviewService.getAllReviewsByProductId(anyLong(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/no-photo-reviews/product/1")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andDo(print());
    }

    @Test
    void testUpdateReview() throws Exception {
        NoPhotoReviewRequestDTO requestDTO = new NoPhotoReviewRequestDTO();
        requestDTO.setScore((byte) 4);
        requestDTO.setContent("Updated content");
        requestDTO.setClientId(1L);
        requestDTO.setOrderDetailId(1L);
        requestDTO.setProductId(1L);

        NoPhotoReviewResponseDTO responseDTO = new NoPhotoReviewResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setScore((byte) 4);
        responseDTO.setContent("Updated content");
        responseDTO.setRegisterDate(LocalDateTime.now());
        responseDTO.setLastModifyDate(LocalDateTime.now());

        when(noPhotoReviewService.updateReview(anyLong(), any(NoPhotoReviewRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/no-photo-reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.content").value("Updated content"))
            .andDo(print());
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(noPhotoReviewService).deleteReview(anyLong());

        mockMvc.perform(delete("/no-photo-reviews/1"))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

    @Test
    void testDeleteReview_NotFound() throws Exception {
        doThrow(new ReviewNotFoundException("리뷰를 찾을 수 없습니다. ID : 1")).when(noPhotoReviewService).deleteReview(anyLong());

        mockMvc.perform(delete("/no-photo-reviews/1"))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
}