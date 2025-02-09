package com.puzzling.assessment.services.impl;

import com.puzzling.assessment.services.interfaces.WordFrequency;

public record WordFrequencyImpl(String word, int frequency) implements WordFrequency {

}
