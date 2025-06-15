package com.assignment.wordanalyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.wordanalyze.dto.FilterRequest;
import com.assignment.wordanalyze.dto.FilterResponse;
import com.assignment.wordanalyze.service.WordAnalyzeService;

import jakarta.validation.Valid;
import lombok.Data;

@Validated
@Data
@RestController
@RequestMapping("/api/words")
public class WordAnalyzeController {
	
	@Autowired
	private final WordAnalyzeService service;

	
    @PostMapping("/analyze")
    public ResponseEntity<FilterResponse> analyze(@Valid @RequestBody FilterRequest request) {
        return ResponseEntity.ok(service.analyze(request));
    }
}
