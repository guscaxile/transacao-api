package com.desafio_itau.transacao_api.business.service;

import com.desafio_itau.transacao_api.controller.dto.EstatisticasResponseDto;
import com.desafio_itau.transacao_api.controller.dto.TransacaoRequestDto;
import com.desafio_itau.transacao_api.infrastructure.exception.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;
    TransacaoRequestDto transacao;
    EstatisticasResponseDto estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDto(20.0, OffsetDateTime.now());
        estatistica = new EstatisticasResponseDto(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso() {
        transacaoService.adicionarTransacoes(transacao);
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(5000);
        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDto(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não pode ser menor que 0.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataOuHoraMaiorQueAtual(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDto(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora maiores que a data e hora atuais.", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso() {
        transacaoService.limparTransacoes();
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(5000);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacoesDentroDoIntervalo(){
        TransacaoRequestDto dto = new TransacaoRequestDto(10.0, OffsetDateTime.now().minusHours(1));
        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(60);
        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }
}
