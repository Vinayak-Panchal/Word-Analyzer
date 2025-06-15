package com.assignment.wordanalyze.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.wordanalyze.dto.FilterRequest;
import com.assignment.wordanalyze.dto.FilterResponse;
import com.assignment.wordanalyze.rules.WordFilterRule;

import lombok.Data;

@Data
@Service
public class WordAnalyzeService {
	
	private final List<WordFilterRule> rules;

    public FilterResponse analyze(FilterRequest request) {
        FilterResponse response = new FilterResponse();

        for (WordFilterRule rule : rules) {
            if (request.getFilters().containsKey(rule.getName())) {
                Object result = rule.process(request.getWords(), request.getFilters());
                response.addResult(rule.getName(), result);
            }
        }

        return response;
    }
}
