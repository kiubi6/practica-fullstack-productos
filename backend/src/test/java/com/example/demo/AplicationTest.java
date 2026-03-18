package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApplicationTest {

    @Test
    void mainTest() {

        assertDoesNotThrow(() -> {

            Application.main(new String[] {});
        });
    }
}