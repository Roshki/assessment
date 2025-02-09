package com.puzzling.assessment.controller;

import com.puzzling.assessment.services.interfaces.WordFrequencyAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordFrequencyController.class)
class WordFrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    WordFrequencyAnalyzer wordFrequencyAnalyzer;

    private String largeText;

    @BeforeEach
    void setUp() {

        largeText = "A".repeat(1024 * 1024 * 600);
    }

    @Test
    void getHighestFreq_largeText() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/highestFreq")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(largeText))
                .andExpect(status().isOk());
    }

}