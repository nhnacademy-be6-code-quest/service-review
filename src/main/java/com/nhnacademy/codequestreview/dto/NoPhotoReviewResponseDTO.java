package com.nhnacademy.codequestreview.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoPhotoReviewResponseDTO {
    private Long id;
    private byte score;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime lastModifyDate;
    private int point;
    private Long clientId;
    private Long orderDetailId;
}
