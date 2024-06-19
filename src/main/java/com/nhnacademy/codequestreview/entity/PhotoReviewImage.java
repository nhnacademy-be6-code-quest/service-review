package com.nhnacademy.codequestreview.entity;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "photo_review_image")
public class PhotoReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_review_image_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "photo_review_id", nullable = false)
    private PhotoReview photoReview;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    public PhotoReviewImage(PhotoReview photoReview, String photoUrl) {
        this.photoReview = photoReview;
        this.photoUrl = photoUrl;
    }
}
