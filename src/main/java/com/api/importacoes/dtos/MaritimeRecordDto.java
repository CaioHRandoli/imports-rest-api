package com.api.importacoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MaritimeRecordDto(@NotBlank String originCountry,
                                @NotBlank String destinationCountry,
                                @NotBlank String cargoDescription,
                                @NotNull BigDecimal cargoValue,
                                @NotNull Integer boxQuantity) {
}
