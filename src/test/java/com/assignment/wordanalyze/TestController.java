package com.assignment.wordanalyze;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import com.assignment.wordanalyze.controller.WordAnalyzeController;
import com.assignment.wordanalyze.rules.MinLengthRule;
import com.assignment.wordanalyze.rules.StartsWithRule;
import com.assignment.wordanalyze.service.WordAnalyzeService;


@WebMvcTest(controllers = WordAnalyzeController.class)
@AutoConfigureMockMvc
@Import({StartsWithRule.class, MinLengthRule.class, WordAnalyzeService.class})
class TestController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBadRequestValidation() throws Exception {
        String invalidPayload = "{}";

        mockMvc.perform(post("/api/words/analyze")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testAnalyzeSuccessFromJsonFile() throws Exception {
        byte[] json = Files.readAllBytes(Paths.get("src/main/resources/input.json"));

        mockMvc.perform(post("/api/words/analyze")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.startsWith").value(3))
                .andDo(print());
    }
    
    @Test
    void testMalformedJson() throws Exception {
    	String malformedJson = "{ \"words\": [\"Mouse\", \"man\"], \"filters\": ";

        mockMvc.perform(post("/api/words/analyze")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Malformed JSON: Please ensure 'words' is a non-empty list and the syntax is valid."))
                .andDo(print());
    }
    
}