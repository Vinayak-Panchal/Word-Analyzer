package com.assignment.wordanalyze.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FilterResponse {
	private Map<String, Object> results = new HashMap<>();

    public void addResult(String key, Object value) {
        results.put(key, value);
    }
}
