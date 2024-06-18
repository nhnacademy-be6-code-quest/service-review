package com.nhnacademy.codequestreview.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "no_photo_review")
public class NoPhotoReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_photo_review_id")
    private Long id;

    @Column(name = "no_photo_review_score", nullable = false)
    private byte score;

    @Column(name = "no_photo_review_content", length = 10)
    private String content;

    @Column(name = "no_photo_review_register_date", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "no_photo_review_last_modify_date")
    private LocalDateTime lastModifyDate;

    @Column(name = "no_photo_review_point", nullable = false)
    private int point;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "order_detail_id", nullable = false)
    private Long orderDetailId;
}
