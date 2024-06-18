package com.nhnacademy.codequestreview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "photo_review_image")
public class PhotoReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_review_image_id")
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "photo_review_id", nullable = false)
    private PhotoReview photoReview;

    @Setter
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    public PhotoReviewImage(PhotoReview photoReview, String photoUrl) {
        this.photoReview = photoReview;
        this.photoUrl = photoUrl;
    }
}
