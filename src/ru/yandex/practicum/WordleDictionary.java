package ru.yandex.practicum;
import java.util.*;
import java.util.List;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words;
    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        Random r = new Random();
        return words.get(r.nextInt(words.size()));
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

}
