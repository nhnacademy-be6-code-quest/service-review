package com.nhnacademy.codequestreview.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class ReviewCreationExceptionTest {

    @Test
    void testReviewCreationExceptionWithMessage() {
        ReviewCreationException exception = new ReviewCreationException("Review creation error");

        assertThat(exception.getMessage()).isEqualTo("Review creation error");
    }
}
