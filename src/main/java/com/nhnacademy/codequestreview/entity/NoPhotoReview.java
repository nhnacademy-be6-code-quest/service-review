package com.nhnacademy.codequestreview.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "no_photo_review")
public class NoPhotoReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_photo_review_id")
    private Long id;

    @Setter
    @Min(1)
    @Max(5)
    @Column(name = "no_photo_review_score", nullable = false)
    private byte score;  // TINYINT에 해당하는 byte 타입

    @Setter
    @Column(name = "no_photo_review_content", length = 10)
    private String content;

    @Setter
    @Column(name = "no_photo_review_register_date", nullable = false)
    private LocalDateTime registerDate;

    @Setter
    @Column(name = "no_photo_review_last_modify_date", nullable = false) // null 허용
    private LocalDateTime lastModifyDate;

    @Setter
    @Column(name = "no_photo_review_point", nullable = false)
    private int point;

    @Setter
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Setter
    @Column(name = "order_detail_id", nullable = false)
    private Long orderDetailId;

    public NoPhotoReview() {
        this.registerDate = LocalDateTime.now();
        this.point = 200;
    }

    public NoPhotoReview(byte score, String content) {
        this.score = score;
        this.content = content;
        this.registerDate = LocalDateTime.now();
        this.point = 200;
    }

}
