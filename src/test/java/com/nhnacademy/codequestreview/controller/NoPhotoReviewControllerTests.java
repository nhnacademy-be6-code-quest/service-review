package com.nhnacademy.codequestreview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.codequestreview.dto.request.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.response.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.service.NoPhotoReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoPhotoReviewController.class)
class NoPhotoReviewControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoPhotoReviewService noPhotoReviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private NoPhotoReviewRequestDTO noPhotoReviewRequestDTO;
    private NoPhotoReviewResponseDTO noPhotoReviewResponseDTO;

    @BeforeEach
    void setUp() {
        noPhotoReviewRequestDTO = new NoPhotoReviewRequestDTO();
        noPhotoReviewRequestDTO.setScore((byte) 5);
        noPhotoReviewRequestDTO.setContent("Great!");
        noPhotoReviewRequestDTO.setClientId(12345L);
        noPhotoReviewRequestDTO.setOrderDetailId(67890L);
        noPhotoReviewRequestDTO.setProductId(12345L);

        noPhotoReviewResponseDTO = new NoPhotoReviewResponseDTO();
        noPhotoReviewResponseDTO.setId(1L);
        noPhotoReviewResponseDTO.setScore((byte) 5);
        noPhotoReviewResponseDTO.setContent("Great!");
        noPhotoReviewResponseDTO.setClientId(12345L);
        noPhotoReviewResponseDTO.setOrderDetailId(67890L);
        noPhotoReviewResponseDTO.setProductId(12345L);
        noPhotoReviewResponseDTO.setRegisterDate(LocalDateTime.now());
        noPhotoReviewResponseDTO.setLastModifyDate(LocalDateTime.now());
        noPhotoReviewResponseDTO.setPoint(200);
    }

    @Test
    void createReviewTest() throws Exception {
        Mockito.when(noPhotoReviewService.createReview(any(NoPhotoReviewRequestDTO.class)))
                .thenReturn(noPhotoReviewResponseDTO);

        mockMvc.perform(post("/no-photo-reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noPhotoReviewRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(noPhotoReviewResponseDTO)));
    }

    @Test
    void getReviewByIdTest() throws Exception {
        Mockito.when(noPhotoReviewService.getReviewById(anyLong()))
                .thenReturn(Optional.of(noPhotoReviewResponseDTO));

        mockMvc.perform(get("/no-photo-reviews/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noPhotoReviewResponseDTO)));
    }

    // TODO remake test
//    @Test
//    void getAllReviewsTest() throws Exception {
//        List<NoPhotoReviewResponseDTO> responseDTOList = Collections.singletonList(noPhotoReviewResponseDTO);
//        Mockito.when(noPhotoReviewService.getAllReviews())
//                .thenReturn(responseDTOList);
//
//        mockMvc.perform(get("/no-photo-reviews")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(responseDTOList)));
//    }

    @Test
    void updateReviewTest() throws Exception {
        Mockito.when(noPhotoReviewService.updateReview(anyLong(), any(NoPhotoReviewRequestDTO.class)))
                .thenReturn(noPhotoReviewResponseDTO);

        mockMvc.perform(put("/no-photo-reviews/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noPhotoReviewRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noPhotoReviewResponseDTO)));
    }

    @Test
    void deleteReviewTest() throws Exception {
        Mockito.doNothing().when(noPhotoReviewService).deleteReview(anyLong());

        mockMvc.perform(delete("/no-photo-reviews/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
