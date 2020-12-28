package io.pidabrow.languageprocessing.dto;

import io.pidabrow.languageprocessing.enumeration.Mode;
import lombok.Builder;

import java.util.List;

@Builder
public class ResultDto {
    List<String> result;
    private String text;
    private Mode mode;
    private Integer maxProductLength;
    private String maxGapLength;
}
