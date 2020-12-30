package io.pidabrow.languageprocessing.dto;

import io.pidabrow.languageprocessing.enumeration.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class InputDto {

    @NotBlank
    private String text;

    @NotNull
    private Mode mode;

    private Integer maxProductLength;
    private Integer maxGapLength;
}
