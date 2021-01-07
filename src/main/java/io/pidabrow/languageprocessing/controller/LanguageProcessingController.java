package io.pidabrow.languageprocessing.controller;

import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import io.pidabrow.languageprocessing.enumeration.Mode;
import io.pidabrow.languageprocessing.service.NGramService;
import io.pidabrow.languageprocessing.service.SkipGramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LanguageProcessingController {

    private final NGramService nGramService;
    private final SkipGramService skipGramService;

    @PutMapping("/n-grams")
    public ResponseEntity<ResultDto> handleGenerateNGrams(InputDto inputDto) {
        return new ResponseEntity<>(nGramService.generateNGrams(inputDto), HttpStatus.OK);
    }

    @GetMapping("/n-grams/demo")
    public ResponseEntity<ResultDto> handleGenerateNGramsDemo() {
        InputDto inputDto = InputDto.builder()
                .text("The quick brown fox jumps over the lazy dog")
                .maxProductLength(3)
                .mode(Mode.N_GRAM)
                .build();

        return new ResponseEntity<>(nGramService.generateNGrams(inputDto), HttpStatus.OK);
    }

    @GetMapping("/skip-grams/demo")
    public ResponseEntity<ResultDto> handleGenerateSkipGrams() {
        InputDto inputDto = InputDto.builder()
                .text("The quick brown fox jumps over the lazy dog")
                .maxProductLength(3)
                .maxGapLength(3)
                .mode(Mode.SKIP_GRAM)
                .build();
        return new ResponseEntity<>(skipGramService.generateSkipGrams(inputDto), HttpStatus.OK);
    }
}
