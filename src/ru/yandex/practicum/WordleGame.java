package ru.yandex.practicum;

import java.util.*;

public class WordleGame {

    private final String answer;
    private int steps = 6;
    private final WordleDictionary dictionary;

    private final List<String> usedWords = new ArrayList<>();

    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
    }


    public String tryWord(String word) throws WordNotFoundInDictionary {

        word = normalize(word);

        if (word.isEmpty()) {
            return suggestWord();
        }

        if (!dictionary.contains(word)) {
            throw new WordNotFoundInDictionary("Слова нет в словаре");
        }

        if (usedWords.contains(word)) {
            throw new IllegalArgumentException("Слово уже вводили");
        }

        if (steps <= 0) {
            throw new IllegalStateException("Ходы закончились");
        }

        usedWords.add(word);
        steps--;

        return checkWord(word);
    }


    private String checkWord(String word) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char c = word.charAt(i);

            if (c == answer.charAt(i)) {
                result.append('+');
            } else if (answer.contains(String.valueOf(c))) {
                result.append('^');
            } else {
                result.append('-');
            }
        }

        return result.toString();
    }


    public String suggestWord() {

        for (String w : dictionary.getWords()) {
            if (!usedWords.contains(w)) {
                return w;
            }
        }

        return "нет подходящих слов";
    }


    public boolean isWin(String word) {
        return word.equals(answer);
    }


    public int getSteps() {
        return steps;
    }

    public String getAnswer() {
        return answer;
    }


    private String normalize(String word) {
        return word.toLowerCase().replace('ё', 'е');
    }
}
