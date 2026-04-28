package ru.yandex.practicum;

import java.io.*;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {

        WordleGame game = null;

        try (
                PrintWriter log = new PrintWriter(new FileWriter("log.txt", true));
                Scanner scanner = new Scanner(System.in)
        ) {

            log.println("=== ИГРА НАЧАЛАСЬ ===");

            // 1. загрузка словаря
            WordleDictionary dictionary =
                    WordleDictionaryLoader.load("words_ru.txt");

            // 2. создание игры
            game = new WordleGame(dictionary);

            log.println("Загаданное слово: " + game.getAnswer());

            // 3. игровой цикл
            while (game.getSteps() > 0) {

                System.out.println("Введите слово (или Enter для подсказки):");
                String input = scanner.nextLine();

                try {

                    String result = game.tryWord(input);

                    System.out.println(result);
                    log.println("Ввод: " + input + " -> " + result);

                    // проверка победы
                    if (game.isWin(input)) {
                        System.out.println("🎉 Вы победили!");
                        log.println("Победа игрока");
                        return;
                    }

                } catch (WordNotFoundInDictionary e) {
                    System.out.println("Слово нет в словаре!");
                    log.println("Ошибка: " + e.getMessage());

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    log.println("Ошибка: " + e.getMessage());

                } catch (Exception e) {
                    System.out.println("Ошибка игры");
                    log.println("КРИТИЧЕСКАЯ ОШИБКА: " + e.getMessage());
                }
            }

            // если попытки закончились
            System.out.println("Вы проиграли!");
            System.out.println("Загаданное слово: " + game.getAnswer());

            log.println("Игра завершена. Проигрыш игрока.");

        } catch (IOException e) {
            System.out.println("Ошибка загрузки файла или логов");
            e.printStackTrace();
        }
    }
}
