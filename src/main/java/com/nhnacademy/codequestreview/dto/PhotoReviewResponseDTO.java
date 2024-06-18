package com.nhnacademy.codequestreview.dto;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PhotoReviewResponseDTO {
    private Long id;
    private byte score;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime lastModifyDate;
    private int point;
    private Long clientId;
    private Long orderDetailId;
    private List<String> photoUrls;
}
