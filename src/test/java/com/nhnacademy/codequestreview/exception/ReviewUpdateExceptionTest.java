package com.nhnacademy.codequestreview.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class ReviewUpdateExceptionTest {

    @Test
    void testReviewUpdateExceptionWithMessage() {
        ReviewUpdateException exception = new ReviewUpdateException("Review update error");

        assertThat(exception.getMessage()).isEqualTo("Review update error");
    }
}
