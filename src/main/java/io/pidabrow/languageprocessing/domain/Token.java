package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {
    private String value;
    private int tokenId;
}
