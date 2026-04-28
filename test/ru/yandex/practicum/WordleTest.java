package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



    class WordleTest {

        private WordleDictionary createDictionary() {
            return new WordleDictionary(List.of("герой", "гонец", "мирон"));
        }

        @Test
        void testGameCreation() {
            WordleGame game = new WordleGame(createDictionary());

            assertNotNull(game.getAnswer());
            assertEquals(6, game.getSteps());
        }

        @Test
        void testCorrectWordWin() throws Exception {
            WordleDictionary dict = new WordleDictionary(List.of("герой"));
            WordleGame game = new WordleGame(dict);

            String result = game.tryWord("герой");

            assertEquals("+++++", result);
            assertTrue(game.isWin("герой"));
        }

        @Test
        void testWrongWordReturnsPattern() throws Exception {
            WordleDictionary dict = new WordleDictionary(List.of("герой"));
            WordleGame game = new WordleGame(dict);

            String result = game.tryWord("гонец");

            assertEquals(5, result.length());

            assertTrue(
                    result.contains("+") ||
                            result.contains("^") ||
                            result.contains("-")
            );
        }

        @Test
        void testWordNotInDictionaryThrowsException() {
            WordleGame game = new WordleGame(createDictionary());

            assertThrows(WordNotFoundInDictionary.class, () -> {
                game.tryWord("abcde");
            });
        }

        @Test
        void testStepsDecreaseAfterValidMove() throws Exception {
            WordleGame game = new WordleGame(createDictionary());

            int before = game.getSteps();

            game.tryWord("гонец");

            assertEquals(before - 1, game.getSteps());
        }

        @Test
        void testEmptyInputReturnsSuggestion() throws Exception {
            WordleGame game = new WordleGame(createDictionary());

            String suggestion = game.tryWord("");

            assertNotNull(suggestion);
            assertFalse(suggestion.isEmpty());
        }
    }

