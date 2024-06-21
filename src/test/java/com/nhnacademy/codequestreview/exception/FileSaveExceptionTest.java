package com.nhnacademy.codequestreview.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class FileSaveExceptionTest {

    @Test
    void testFileSaveExceptionWithMessageAndCause() {
        Throwable cause = new Throwable("Root cause");
        FileSaveException exception = new FileSaveException("File save error", cause);

        assertThat(exception.getMessage()).isEqualTo("File save error");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
