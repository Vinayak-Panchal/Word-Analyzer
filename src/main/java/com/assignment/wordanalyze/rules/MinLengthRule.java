package com.assignment.wordanalyze.rules;



import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MinLengthRule implements WordFilterRule {
	public boolean matches(String word, Map<String, Object> filters) {
        Object minLen = filters.get("minLength");
        return minLen instanceof Number && word.length() >= ((Number) minLen).intValue();
    }

    public String getName() { return "minLength"; }

    public Object process(List<String> words, Map<String, Object> filters) {
        return words.stream().filter(word -> matches(word, filters)).collect(Collectors.toList());
    }
}
