package com.desafio_itau.transacao_api.controller;

import com.desafio_itau.transacao_api.business.service.EstatisticaService;
import com.desafio_itau.transacao_api.controller.dto.EstatisticasResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<EstatisticasResponseDto> buscarEstatisticas(
            @RequestParam(value = "IntervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca){

        return ResponseEntity.ok(estatisticaService.calcularEstatisticaTransacoes(intervaloBusca));
    }
}
