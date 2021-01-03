package io.pidabrow.languageprocessing.dto;

import io.pidabrow.languageprocessing.enumeration.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class InputDto {

    @NotBlank
    private final String text;

    @NotNull
    private final Mode mode;

    private final Integer maxProductLength;
    private final Integer maxGapLength;
}
