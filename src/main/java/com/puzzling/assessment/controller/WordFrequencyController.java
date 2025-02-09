package com.puzzling.assessment.controller;


import com.puzzling.assessment.services.interfaces.WordFrequency;
import com.puzzling.assessment.services.interfaces.WordFrequencyAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordFrequencyController {
   private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @PostMapping("/highestFreq")
    public int getHighestFreq(@RequestBody String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @PostMapping("/freqForWord/{word}")
    public int getFreqForWord(@RequestBody String text, @PathVariable String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    @PostMapping("/mostFreqNWords/{n}")
    public List<WordFrequency> getMostFreqNWords(@RequestBody String text, @PathVariable int n) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
    }


}
