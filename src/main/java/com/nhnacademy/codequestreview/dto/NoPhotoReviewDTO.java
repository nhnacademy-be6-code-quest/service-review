package com.nhnacademy.codequestreview.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class NoPhotoReviewDTO {
    private Long id;
    private byte score;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime lastModifyDate;
    private int point;
    private Long clientId;
    private Long orderDetailId;
}
