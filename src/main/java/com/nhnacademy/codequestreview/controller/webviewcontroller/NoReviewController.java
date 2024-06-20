package com.nhnacademy.codequestreview.controller.webviewcontroller;


import com.nhnacademy.codequestreview.client.NoPhotoReviewClient;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewRequestDTO;
import com.nhnacademy.codequestreview.dto.NoPhotoReviewResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class NoReviewController {

    private final NoPhotoReviewClient noPhotoReviewClient;


    @GetMapping("/view/no-photo-reviews")
    public String getNoPhotoReviews(Model model) {
        ResponseEntity<List<NoPhotoReviewResponseDTO>> responseEntity = noPhotoReviewClient.getAllReviews();
        List<NoPhotoReviewResponseDTO> reviews = responseEntity.getBody();
        model.addAttribute("reviews", reviews);
        return "no-photo-reviews";
    }

    @GetMapping("/view/add-no-photo-review")
    public String addNoPhotoReviewForm(Model model) {
        model.addAttribute("review", new NoPhotoReviewRequestDTO());
        return "add-no-photo-review";
    }

    @PostMapping("/view/add-no-photo-review")
    public String createNoPhotoReview(
        @Validated @ModelAttribute("review") NoPhotoReviewRequestDTO requestDTO) {
        ResponseEntity<NoPhotoReviewResponseDTO> responseEntity = noPhotoReviewClient.createReview(
            requestDTO);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/view/no-photo-reviews";
        } else {
            throw new RuntimeException("리뷰를 생성하는데 실패하였습니다.");
        }
    }

    @GetMapping("/view/edit-no-photo-review/{id}")
    public String updateNoPhotoReviewForm(@PathVariable("id") Long id, Model model) {
        ResponseEntity<NoPhotoReviewResponseDTO> responseEntity = noPhotoReviewClient.getReviewById(
            id);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("review", responseEntity.getBody());
            return "edit-no-photo-review";
        } else {
            throw new RuntimeException("리뷰를 수정하는데 실패하였습니다.");
        }
    }

    @PostMapping("/view/edit-no-photo-review/{id}")
    public String updateNoPhotoReview(
        @PathVariable("id") Long id,
        @Valid @ModelAttribute("review") NoPhotoReviewRequestDTO requestDTO) {

        ResponseEntity<NoPhotoReviewResponseDTO> responseEntity = noPhotoReviewClient.updateReview(
            id, requestDTO);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/view/no-photo-reviews";
        } else {
            throw new RuntimeException("리뷰를 수정하는데 실패하였습니다.");
        }
    }

}
