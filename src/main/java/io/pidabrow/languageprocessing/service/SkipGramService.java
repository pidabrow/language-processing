package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkipGramService {

    public ResultDto generateSkipGrams(InputDto inputDto) {
        List<String> skipGrams = new ArrayList<>();

        int maxGapLength = inputDto.getMaxGapLength();

        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(skipGrams)
                .build();
    }
}
