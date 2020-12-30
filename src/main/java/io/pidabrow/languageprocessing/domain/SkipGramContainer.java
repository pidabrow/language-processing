package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SkipGramContainer {
    private final List<Token> tokens;
    private final List<Integer> gapSizes;
    private final int skipGramLength;

    public SkipGramContainer(List<Token> tokens) {
        this.tokens = tokens;
        this.skipGramLength = tokens.size();
        this.gapSizes = analyseGapSizes();
    }

    private List<Integer> analyseGapSizes() {
        return List.of(0);
    }

    @Override
    public String toString() {
        return tokens.toArray().toString();
    }
}
