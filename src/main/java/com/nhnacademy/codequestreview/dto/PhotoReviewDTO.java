package com.nhnacademy.codequestreview.dto;


import com.nhnacademy.codequestreview.entity.PhotoReviewImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Getter
public class PhotoReviewDTO {
    private Long id;
    private byte score;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime lastModifyDate;
    private int point;
    private Long clientId;
    private Long orderDetailId;
}
