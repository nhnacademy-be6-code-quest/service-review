package com.nhnacademy.codequestreview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoReviewImageDTO {
    private Long id;
    private Long photoReviewId;
    private String photoUrl;
}
