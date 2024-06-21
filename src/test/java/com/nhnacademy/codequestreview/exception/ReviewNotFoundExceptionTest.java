package com.nhnacademy.codequestreview.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class ReviewNotFoundExceptionTest {

    @Test
    void testReviewNotFoundExceptionWithMessage() {
        ReviewNotFoundException exception = new ReviewNotFoundException("Review not found");

        assertThat(exception.getMessage()).isEqualTo("Review not found");
    }
}
