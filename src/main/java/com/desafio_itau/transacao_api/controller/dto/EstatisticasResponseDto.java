package com.desafio_itau.transacao_api.controller.dto;

public record EstatisticasResponseDto(Long count, Double sum, Double avg, Double min, Double max) {
}
