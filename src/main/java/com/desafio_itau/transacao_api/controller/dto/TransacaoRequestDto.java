package com.desafio_itau.transacao_api.controller.dto;

import java.time.OffsetDateTime;

public record TransacaoRequestDto(Double valor, OffsetDateTime dataHora) {
}
