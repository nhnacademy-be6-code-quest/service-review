package com.nhnacademy.codequestreview.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class ReviewPhotoProcessingExceptionTest {

    @Test
    void testReviewPhotoProcessingExceptionWithMessageAndCause() {
        Throwable cause = new Throwable("Root cause");
        ReviewPhotoProcessingException exception = new ReviewPhotoProcessingException("Photo processing error", cause);

        assertThat(exception.getMessage()).isEqualTo("Photo processing error");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
