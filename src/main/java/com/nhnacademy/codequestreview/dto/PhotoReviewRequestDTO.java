package com.nhnacademy.codequestreview.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;


@Data
public class PhotoReviewRequestDTO {
    @Min(1)
    @Max(5)
    @NotNull(message = "평가점수는 필수 입력 항목입니다.")
    private byte score;

    @Size(max = 1000)
    @NotBlank(message = "리뷰내용은 필수 입력 항목입니다.")
    private String content;

    @NotNull
    private Long clientId;

    @NotNull
    private Long orderDetailId;

    private List<String> photoUrls;
}
