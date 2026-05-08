package com.desafio_itau.transacao_api.business.service;

import com.desafio_itau.transacao_api.controller.dto.EstatisticasResponseDto;
import com.desafio_itau.transacao_api.controller.dto.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDto calcularEstatisticaTransacoes(Integer intervaloBusca) {
        log.info("Iniciada a busca de estatísticas de transações pelo período de tempo " + intervaloBusca);
        long start = System.currentTimeMillis();
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDto::valor).summaryStatistics();

        long finish = System.currentTimeMillis();
        long tempoRequisicao = finish - start;
        System.out.println("Tempo de requisição: " + tempoRequisicao);

        log.info("Estatísticas retornadas com sucesso.");
        return new EstatisticasResponseDto(estatisticasTransacoes.getCount(), estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(), estatisticasTransacoes.getMin(), estatisticasTransacoes.getMax());
    }
}
