package ru.yandex.practicum;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordleDictionaryLoader {

    private static final int WORD_LENGTH = 5;

    public static WordleDictionary load(String filePath) throws IOException {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = normalize(line);
                if (line.length() == WORD_LENGTH) {
                    words.add(line);
                }
            }
        }

        return new WordleDictionary(words);
    }

    private static String normalize(String word) {
        return word.toLowerCase().replace('ё', 'е');
    }
}
