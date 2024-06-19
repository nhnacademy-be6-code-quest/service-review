package com.nhnacademy.codequestreview.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PhotoReviewResponseDTO {
    private Long id;
    private byte score;
    private String content;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifyDate;

    private int point;
    private Long clientId;
    private Long orderDetailId;
    private List<String> photoUrls;

//    public String getPhotoUrlsAsJson() {
//        try {
//            return new ObjectMapper().writeValueAsString(this.photoUrls);
//        } catch (Exception e) {
//            return "[]";
//        }
//    }
//
//    public String getStars() {
//        return "★".repeat(this.score);
//    }

}
