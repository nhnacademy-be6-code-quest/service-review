package com.nhnacademy.codequestreview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.service.PhotoReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhotoReviewController.class)
public class PhotoReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoReviewService photoReviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private PhotoReviewRequestDTO photoReviewRequestDTO;
    private PhotoReviewResponseDTO photoReviewResponseDTO;

    @BeforeEach
    void setUp() {
        photoReviewRequestDTO = new PhotoReviewRequestDTO();
        photoReviewRequestDTO.setScore((byte) 5);
        photoReviewRequestDTO.setContent("Great");
        photoReviewRequestDTO.setClientId(1L);
        photoReviewRequestDTO.setOrderDetailId(1L);
        photoReviewRequestDTO.setPhotoUrls(Arrays.asList("url1", "url2"));

        photoReviewResponseDTO = new PhotoReviewResponseDTO();
        photoReviewResponseDTO.setId(1L);
        photoReviewResponseDTO.setScore((byte) 5);
        photoReviewResponseDTO.setContent("Great");
        photoReviewResponseDTO.setRegisterDate(LocalDateTime.now());
        photoReviewResponseDTO.setLastModifyDate(LocalDateTime.now());
        photoReviewResponseDTO.setPoint(500);
        photoReviewResponseDTO.setClientId(1L);
        photoReviewResponseDTO.setOrderDetailId(1L);
        photoReviewResponseDTO.setPhotoUrls(Arrays.asList("url1", "url2"));
    }

    @Test
    void createReviewTest() throws Exception {
        given(photoReviewService.createReview(any(PhotoReviewRequestDTO.class))).willReturn(photoReviewResponseDTO);

        mockMvc.perform(post("/photo-reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(photoReviewRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(photoReviewResponseDTO.getId()))
                .andExpect(jsonPath("$.score").value((int) photoReviewResponseDTO.getScore()))
                .andExpect(jsonPath("$.content").value(photoReviewResponseDTO.getContent()));
    }

    @Test
    void getReviewByIdTest() throws Exception {
        given(photoReviewService.getReviewById(anyLong())).willReturn(Optional.of(photoReviewResponseDTO));

        mockMvc.perform(get("/photo-reviews/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(photoReviewResponseDTO.getId()))
                .andExpect(jsonPath("$.score").value((int) photoReviewResponseDTO.getScore()))
                .andExpect(jsonPath("$.content").value(photoReviewResponseDTO.getContent()));
    }

    @Test
    void getAllReviewsTest() throws Exception {
        given(photoReviewService.getAllReviews()).willReturn(Arrays.asList(photoReviewResponseDTO));

        mockMvc.perform(get("/photo-reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(photoReviewResponseDTO.getId()))
                .andExpect(jsonPath("$[0].score").value((int) photoReviewResponseDTO.getScore()))
                .andExpect(jsonPath("$[0].content").value(photoReviewResponseDTO.getContent()));
    }

    @Test
    void updateReviewTest() throws Exception {
        given(photoReviewService.updateReview(anyLong(), any(PhotoReviewRequestDTO.class))).willReturn(photoReviewResponseDTO);

        mockMvc.perform(put("/photo-reviews/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(photoReviewRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(photoReviewResponseDTO.getId()))
                .andExpect(jsonPath("$.score").value((int) photoReviewResponseDTO.getScore()))
                .andExpect(jsonPath("$.content").value(photoReviewResponseDTO.getContent()));
    }

    @Test
    void deleteReviewTest() throws Exception {
        mockMvc.perform(delete("/photo-reviews/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
