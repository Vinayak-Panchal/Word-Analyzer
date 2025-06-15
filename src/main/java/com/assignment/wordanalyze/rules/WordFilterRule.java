package com.assignment.wordanalyze.rules;

import java.util.List;
import java.util.Map;

public interface WordFilterRule {
	boolean matches(String word, Map<String, Object> filters);
    String getName();
    Object process(List<String> words, Map<String, Object> filters);
}
