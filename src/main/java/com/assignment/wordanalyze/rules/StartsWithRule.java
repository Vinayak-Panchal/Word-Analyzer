package com.assignment.wordanalyze.rules;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StartsWithRule implements WordFilterRule {
	public boolean matches(String word, Map<String, Object> filters) {
        Object prefix = filters.get("startsWith");
        return prefix instanceof String && word.toLowerCase().startsWith(((String) prefix).toLowerCase());
    }

    public String getName() { return "startsWith"; }

    public Object process(List<String> words, Map<String, Object> filters) {
        long count = words.stream().filter(word -> matches(word, filters)).count();
        return (int) count;
    }
}
