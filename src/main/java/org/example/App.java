package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class App {
      public static void main(String[] args) {
      // Путь к файлу (замените на путь к вашему файлу)
      String filePath = "input.txt";

      // Чтение текста из файла
      String inputText = readFile(filePath);

      if (inputText == null) {
        System.out.println("Не удалось прочитать файл.");
        return;
      }

      // Форматирование текста
      String formattedText = formatText(inputText);

      // Вывод результата
      System.out.println("Исходный текст:");
      System.out.println(inputText);
      System.out.println("\nОтформатированный текст:");
      System.out.println(formattedText);
    }

    /**
     * Метод для чтения текста из файла.
     *
     * @param filePath путь к файлу
     * @return содержимое файла в виде строки или null, если файл не найден
     */
    private static String readFile(String filePath) {
      StringBuilder content = new StringBuilder();
      try {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
          content.append(scanner.nextLine()).append("\n");
        }

        scanner.close();
      } catch (FileNotFoundException e) {
        System.out.println("Файл не найден: " + filePath);
        return null;
      }

      return content.toString();
    }

    /**
     * Метод для форматирования текста (замена телефонных номеров).
     *
     * @param inputText исходный текст
     * @return отформатированный текст
     */
    static String formatText(String inputText) {
      // Регулярное выражение для поиска телефонных номеров
      String phoneRegex = "(\\+?7|8)?[\\s\\-]?\\(?([0-9]{3})\\)?[\\s\\-]?([0-9]{3})[\\s\\-]?([0-9]{2})[\\s\\-]?([0-9]{2})";
      Pattern pattern = Pattern.compile(phoneRegex);
      Matcher matcher = pattern.matcher(inputText);

      // Строка для хранения результата
      StringBuffer result = new StringBuffer();

      // Поиск и замена телефонных номеров
      while (matcher.find()) {
        String countryCode = "+1"; // Новый код страны
        String areaCode = matcher.group(2); // Код региона
        String part1 = matcher.group(3); // Первая часть номера
        String part2 = matcher.group(4); // Вторая часть номера
        String part3 = matcher.group(5); // Третья часть номера

        // Форматирование номера
        String formattedNumber = String.format("%s (%s) %s-%s-%s", countryCode, areaCode, part1, part2, part3);

        // Замена найденного номера на отформатированный
        matcher.appendReplacement(result, formattedNumber);
      }

      // Добавление оставшейся части текста
      matcher.appendTail(result);

      return result.toString();
    }
  }
