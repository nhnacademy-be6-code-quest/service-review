package com.nhnacademy.codequestreview.controller;


import com.nhnacademy.codequestreview.service.NoPhotoReviewService;
import com.nhnacademy.codequestreview.service.PhotoReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class WebController {

    private final PhotoReviewService photoReviewService;

    private final NoPhotoReviewService noPhotoReviewService;

    @GetMapping("/view/photo-reviews")
    public String getPhotoReviews(Model model) {
        model.addAttribute("reviews", photoReviewService.getAllReviews());
        return "photo-reviews";
    }

    @GetMapping("/view/no-photo-reviews")
    public String getNoPhotoReviews(Model model) {
        model.addAttribute("reviews", noPhotoReviewService.getAllReviews());
        return "no-photo-reviews";
    }
}
