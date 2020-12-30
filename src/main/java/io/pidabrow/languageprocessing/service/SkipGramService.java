package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.domain.Token;
import io.pidabrow.languageprocessing.domain.Tree;
import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SkipGramService {

    public ResultDto generateSkipGrams(InputDto inputDto) {
        List<String> skipGrams = new ArrayList<>();

        String[] words = inputDto.getText().split(" ");
        List<Token> tokens = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            tokens.add(new Token(words[i], i));
        }

        int maxGapLength = inputDto.getMaxGapLength();

        Tree skipGramTree = new Tree();
        skipGramTree.populateTree(tokens);


        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(skipGrams)
                .build();
    }
}
