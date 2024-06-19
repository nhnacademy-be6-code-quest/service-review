package com.nhnacademy.codequestreview.dto;


import lombok.Data;
import java.time.LocalDateTime;


@Data
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
