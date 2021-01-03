package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Token {
    private final String value;
    private final int tokenId;
}
