package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
        int[] tokenIds = tokens.stream()
                .map(Token::getTokenId)
                .mapToInt(Integer::intValue)
                .toArray();

        List<Integer> gapSizes = new ArrayList<>();

        for(int i = 0; i < tokenIds.length - 1; i++) {
            int distance = tokenIds[i+1] - tokenIds[i] - 1; // extra -1 is because tokenIds[3] - tokenIds[1] must return 1, not 2
            gapSizes.add(distance);
        }

        return gapSizes;
    }

    @Override
    public String toString() {
        return String.join(" ", tokens.stream().map(Token::getValue).collect(Collectors.toList()))
                + ((gapSizes.isEmpty() || gapSizes.stream().filter(g -> g.intValue() > 0).findAny().isEmpty())
                    ? "" : " (gaps: " + gapSizes.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")");
    }
}
