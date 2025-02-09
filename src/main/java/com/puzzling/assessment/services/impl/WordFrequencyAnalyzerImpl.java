package com.puzzling.assessment.services.impl;

import com.puzzling.assessment.services.interfaces.WordFrequency;
import com.puzzling.assessment.services.interfaces.WordFrequencyAnalyzer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) {
        List<WordFrequency> wordFrequencies = getWordFrequencies(text);

        return wordFrequencies.stream()
                .mapToInt(WordFrequency::frequency)
                .max()
                .orElse(0);
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        word = word.toLowerCase(Locale.getDefault());

        String[] words = text.toLowerCase(Locale.getDefault()).split("[^a-zA-Z0-9]+");
        int frequency = 0;

        for (String w : words) {
            if (w.equals(word)) {
                frequency++;
            }
        }

        return frequency;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        List<WordFrequency> wordFrequencies = getWordFrequencies(text);
        List<WordFrequency> listOfNWords = new ArrayList<>();

        wordFrequencies.sort(Comparator.comparingInt(WordFrequency::frequency)
                .reversed()
                .thenComparing(WordFrequency::word));

        for (int i = 0; i < n; i++) {
            listOfNWords.add(wordFrequencies.get(i));
        }
        return listOfNWords;
    }

    private static List<WordFrequency> getWordFrequencies(String text) {

        String[] words = text.toLowerCase(Locale.getDefault()).split("[^a-zA-Z0-9]+");
        List<WordFrequency> wordFrequencies = new ArrayList<>();

        for (String w : words) {
            Optional<WordFrequency> current = wordFrequencies.stream()
                    .filter(wf -> wf.word().equalsIgnoreCase(w))
                    .findFirst();

            if (current.isPresent()) {
                WordFrequency oldEntry = current.get();
                wordFrequencies.remove(oldEntry);
                wordFrequencies.add(new WordFrequencyImpl(w, oldEntry.frequency() + 1));
            } else {
                wordFrequencies.add(new WordFrequencyImpl(w, 1));
            }
        }
        return wordFrequencies;
    }
}
