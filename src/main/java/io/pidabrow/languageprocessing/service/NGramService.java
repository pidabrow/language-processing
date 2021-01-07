package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class NGramService {

    private static final String WHITESPACE = " ";

    public ResultDto generateNGrams(InputDto inputDto) {
        List<String> nGrams = new ArrayList<>();

        String[] tokens = inputDto.getText().split(" ");
        int maxNGramLength = inputDto.getMaxProductLength();

        IntStream.rangeClosed(1, maxNGramLength).forEach(nGramLength -> {
            for (int currentTokenId = 0; currentTokenId + nGramLength <= tokens.length; currentTokenId++) {

                StringBuilder builder = new StringBuilder();

                for (int nextProductStartId = currentTokenId; nextProductStartId < currentTokenId + nGramLength; nextProductStartId++) {
                    builder.append(tokens[nextProductStartId]).append(WHITESPACE);
                }

                nGrams.add(builder.toString());
            }
        });

        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(nGrams)
                .build();
    }
}
