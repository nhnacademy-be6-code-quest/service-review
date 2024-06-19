package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class WebController {

    private final RestTemplate restTemplate;
    private String photoReviewApiUrl = "http://localhost:8080/photo-reviews";
    private String noPhotoReviewApiUrl = "http://localhost:8080/no-photo-reviews";

    @GetMapping("/view/photo-reviews")
    public String getPhotoReviews(Model model) {
        ResponseEntity<List<PhotoReviewResponseDTO>> responseEntity =
            restTemplate.exchange(photoReviewApiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PhotoReviewResponseDTO>>() {
                });
        List<PhotoReviewResponseDTO> reviews = responseEntity.getBody();
        model.addAttribute("reviews", reviews);
        return "photo-reviews";
    }

    @GetMapping("/view/no-photo-reviews")
    public String getNoPhotoReviews(Model model) {
        ResponseEntity<List<NoPhotoReviewResponseDTO>> responseEntity =
            restTemplate.exchange(noPhotoReviewApiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<NoPhotoReviewResponseDTO>>() {
                });
        List<NoPhotoReviewResponseDTO> reviews = responseEntity.getBody();
        model.addAttribute("reviews", reviews);
        return "no-photo-reviews";
    }
}
