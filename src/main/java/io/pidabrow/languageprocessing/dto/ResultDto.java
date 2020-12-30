package io.pidabrow.languageprocessing.dto;

import io.pidabrow.languageprocessing.enumeration.Mode;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    List<String> result;
    private String text;
    private Mode mode;
    private Integer maxProductLength;
    private String maxGapLength;
}
