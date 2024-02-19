package ru.mts.hw_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Tests of the main application class")
class MainTest {
    @Test
    @DisplayName(value = "Context Test")
    public void contextLoads() {
    }

    @Test
    @DisplayName(value = "Tests of the main class")
    void main() {
        Assertions.assertDoesNotThrow(Main::new);
        Assertions.assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}