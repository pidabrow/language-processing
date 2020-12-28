package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import io.pidabrow.languageprocessing.enumeration.Mode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class NGramService {

    public ResultDto generateNGrams(InputDto inputDto) {
        List<String> nGrams = new ArrayList<>();

        String[] tokens = inputDto.getText().split(" ");
        int maxProductLength = inputDto.getMaxProductLength();

        IntStream.rangeClosed(1, maxProductLength).forEach(i -> {

        });

        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(nGrams)
                .build();
    }
}
