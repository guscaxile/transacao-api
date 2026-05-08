package com.desafio_itau.transacao_api.business.service;

import com.desafio_itau.transacao_api.controller.dto.EstatisticasResponseDto;
import com.desafio_itau.transacao_api.controller.dto.TransacaoRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;
    @Mock
    TransacaoService transacaoService;
    TransacaoRequestDto transacao;
    EstatisticasResponseDto estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDto(20.0, OffsetDateTime.now());
        estatistica = new EstatisticasResponseDto(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticaComSucesso() {
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacao));
        EstatisticasResponseDto resultado = estatisticaService.calcularEstatisticaTransacoes(60);
        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatistica);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia() {
        EstatisticasResponseDto estatisticaEsperado = new EstatisticasResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());
        EstatisticasResponseDto resultado = estatisticaService.calcularEstatisticaTransacoes(60);
        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperado);
    }
}
