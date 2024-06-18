package com.nhnacademy.codequestreview.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "photo_review")
public class PhotoReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_review_id")
    private Long id;

    @Setter
    @Min(1)
    @Max(5)
    @Column(name = "photo_review_score", nullable = false)
    private byte score;  // TINYINT에 해당하는 byte 타입

    @Setter
    @Column(name = "photo_review_content", length = 10)
    private String content;

    @Setter
    @Column(name = "photo_review_register_date", nullable = false)
    private LocalDateTime registerDate;

    @Setter
    @Column(name = "photo_review_last_modify_date") // null 허용
    private LocalDateTime lastModifyDate;

    @Setter
    @Column(name = "photo_review_point", nullable = false)
    private int point;

    @Setter
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Setter
    @Column(name = "order_detail_id", nullable = false)
    private Long orderDetailId;

    @OneToMany(mappedBy = "photoReview", cascade = CascadeType.REMOVE)
    private List<PhotoReviewImage> photoReviewImages;

    public PhotoReview() {
        this.registerDate = LocalDateTime.now();
        this.point = 500;
    }

    public PhotoReview(byte score, String content) {
        this.score = score;
        this.content = content;
        this.registerDate = LocalDateTime.now();
        this.point = 500;
    }
}
