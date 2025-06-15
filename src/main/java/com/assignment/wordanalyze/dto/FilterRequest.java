package com.assignment.wordanalyze.dto;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FilterRequest {
	@NotNull(message = "Word list is required")
    @Size(min = 1, message = "Word list must not be empty")
	private List<String> words;
	
	@NotNull(message = "Filters are required")
    private Map<String, Object> filters;
}
