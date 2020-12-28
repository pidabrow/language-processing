package io.pidabrow.languageprocessing.dto;

import io.pidabrow.languageprocessing.enumeration.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class InputDto {
    private String text;
    private Mode mode;
    private Integer maxProductLength;
    private String maxGapLength;
}
