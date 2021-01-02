package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

        StringBuilder builder = new StringBuilder();
    }

    private List<Integer> analyseGapSizes() {
        return List.of(0);
    }

    @Override
    public String toString() {
        return String.join(" ", tokens.stream().map(Token::getValue).collect(Collectors.toList()));
    }
}
