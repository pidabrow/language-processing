package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Token {
    private String value;
    private int tokenId;
}
