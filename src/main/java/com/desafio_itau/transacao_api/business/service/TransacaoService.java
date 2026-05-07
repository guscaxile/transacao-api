package com.desafio_itau.transacao_api.business.service;

import com.desafio_itau.transacao_api.controller.dto.TransacaoRequestDto;
import com.desafio_itau.transacao_api.infrastructure.exception.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDto> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDto dto) {
        log.info("Iniciado o processamento de gravar transações " + dto);
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data atual.");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais.");
        }
        if (dto.valor() < 0) {
            log.error("Valor não pode ser menor que 0.");
            throw new UnprocessableEntity("Valor não pode ser menos que 0.");
        }

        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso");
    }

    public void limparTransacoes() {
        log.info("Iniciado o processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso.");
    }

    public List<TransacaoRequestDto> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciadas buscas de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso.");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }


}
