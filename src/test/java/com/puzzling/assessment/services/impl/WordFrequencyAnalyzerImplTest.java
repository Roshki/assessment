package com.puzzling.assessment.services.impl;

import com.puzzling.assessment.services.interfaces.WordFrequency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class WordFrequencyAnalyzerImplTest {

    @InjectMocks
    private WordFrequencyAnalyzerImpl wordFrequencyAnalyzer;

    private static final String text = "The cat' jumped over the fence, and the dog watched as the cat ran away. The cat was fast.";

    @Test
    void calculateHighestFrequency() {
        int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(text);
        Assertions.assertEquals(5, highestFrequency);
    }

    @Test
    void calculateFrequencyForWord() {
        int wordFrequency = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "the");
        Assertions.assertEquals(5, wordFrequency);
    }

    @Test
    void calculateFrequencyForWord_ignoredCase() {
        int wordFrequency = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "tHe");
        int wordFrequencyUpper = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "THE");
        int wordFrequencyLower = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "the");
        Assertions.assertEquals(5, wordFrequency);
        Assertions.assertEquals(wordFrequencyLower, wordFrequencyUpper);
        Assertions.assertEquals(wordFrequency, wordFrequencyUpper);
    }

    @Test
    void calculateMostFrequentNWords_sortedByFrequencyThenWord() {
        List<WordFrequency> wordFrequencyList = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, 3);

        List<WordFrequency> expected = List.of(
                new WordFrequencyImpl("the", 5),
                new WordFrequencyImpl("cat", 3),
                new WordFrequencyImpl("and", 1));

        Assertions.assertEquals(expected, wordFrequencyList);
    }
}