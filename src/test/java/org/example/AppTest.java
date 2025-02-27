package org.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

  @Test
  void testFormatText_ValidNumbers() {
    String input = "Телефоны: +7(900)123-45-67, 8-900-123-45-67, 89001234567";
    String expected = "Телефоны: +1 (900) 123-45-67, +1 (900) 123-45-67, +1 (900) 123-45-67";
    String result = App.formatText(input);
    assertEquals(expected, result);
  }

  @Test
  void testFormatText_NoNumbers() {
    String input = "Нет телефонов в тексте.";
    String expected = "Нет телефонов в тексте.";
    String result = App.formatText(input);
    assertEquals(expected, result);
  }

  @Test
  void testFormatText_MixedContent() {
    String input = "Контакты: 8(900)1234567, email@example.com, +7-900-123-45-67";
    String expected = "Контакты: +1 (900) 123-45-67, email@example.com, +1 (900) 123-45-67";
    String result = App.formatText(input);
    assertEquals(expected, result);
  }
}