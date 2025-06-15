package com.assignment.wordanalyze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.wordanalyze.dto.FilterRequest;
import com.assignment.wordanalyze.dto.FilterResponse;
import com.assignment.wordanalyze.rules.MinLengthRule;
import com.assignment.wordanalyze.rules.StartsWithRule;
import com.assignment.wordanalyze.rules.WordFilterRule;
import com.assignment.wordanalyze.service.WordAnalyzeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


class WordAnalyzerApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	private WordAnalyzeService service;

    @BeforeEach
    void setUp() {
        List<WordFilterRule> rules = List.of(
            new StartsWithRule(),
            new MinLengthRule()
        );
        service = new WordAnalyzeService(rules);
    }
	
	@Test
	void testAnalysisFromJsonFile() throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    File file = new File("src/main/resources/input.json");
	    Map<String, Object> rawData = mapper.readValue(file, new TypeReference<>() {});

	    FilterRequest request = new FilterRequest();
	    request.setWords((List<String>) rawData.get("words"));
	    request.setFilters((Map<String, Object>) rawData.get("filters"));

	    FilterResponse res = service.analyze(request);
	    Map<String, Object> expected = (Map<String, Object>) rawData.get("expected");
	    assertEquals(expected, res.getResults());
	}
	
	
	
}
