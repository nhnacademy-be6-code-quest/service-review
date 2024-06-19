package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.dto.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.PhotoReviewResponseDTO;
import com.nhnacademy.codequestreview.service.PhotoReviewService;
import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
public class WebController {

    private final RestTemplate restTemplate;
    private String photoReviewApiUrl = "http://localhost:8080/photo-reviews";
    private String noPhotoReviewApiUrl = "http://localhost:8080/no-photo-reviews";

    @GetMapping("/")
    public String home() {
        return "index";
    }

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

    @GetMapping("/view/add-photo-review")
    public String addPhotoReviewForm(Model model) {
        model.addAttribute("review", new PhotoReviewRequestDTO());
        return "add-photo-review";
    }

    @PostMapping("/view/add-photo-review")
    public String createReview(
        @Valid @ModelAttribute("review") PhotoReviewRequestDTO requestDTO,
        @RequestPart("photoFiles") List<MultipartFile> photoFiles) {

        List<String> photoUrls = photoFiles.stream()
            .map(this::saveFileAndGetUrl)
            .collect(Collectors.toList());

        requestDTO.setPhotoUrls(photoUrls);

        // PhotoReviewController API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PhotoReviewRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

        ResponseEntity<PhotoReviewResponseDTO> responseEntity = restTemplate.exchange(
            photoReviewApiUrl, HttpMethod.POST, requestEntity, PhotoReviewResponseDTO.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return "redirect:/view/photo-reviews";
        } else {
            throw new RuntimeException("Failed to create review");
        }
    }

    @GetMapping("/view/add-no-photo-review")
    public String addNoPhotoReviewForm(Model model) {
        model.addAttribute("review", new NoPhotoReviewRequestDTO());
        return "add-no-photo-review";
    }

    @PostMapping("/view/add-no-photo-review")
    public String createNoPhotoReview(
        @Valid @ModelAttribute("review") NoPhotoReviewRequestDTO requestDTO) {

        ResponseEntity<NoPhotoReviewResponseDTO> responseEntity = restTemplate.postForEntity(
            noPhotoReviewApiUrl, requestDTO, NoPhotoReviewResponseDTO.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return "redirect:/view/no-photo-reviews";
        } else {
            throw new RuntimeException("Failed to create review");
        }
    }

    private String saveFileAndGetUrl(MultipartFile file) {
        // 프로젝트 루트 디렉토리 내의 static/uploads 디렉토리에 저장
        String saveDir = new File("src/main/resources/static/uploads").getAbsolutePath() + "/";

        // Ensure the save directory exists
        File directory = new File(saveDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File savedFile = new File(saveDir + filename);
            file.transferTo(savedFile);
            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }

}
