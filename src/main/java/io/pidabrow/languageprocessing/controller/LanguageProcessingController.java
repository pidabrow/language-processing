package io.pidabrow.languageprocessing.controller;

import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import io.pidabrow.languageprocessing.service.NGramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LanguageProcessingController {

    private final NGramService nGramService;

    @GetMapping("/n-grams")
    public ResponseEntity<ResultDto> handleGenerateNGrams(InputDto inputDto) {
        return new ResponseEntity<>(nGramService.generateNGrams(inputDto), HttpStatus.OK);
    }
}
