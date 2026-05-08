package com.desafio_itau.transacao_api.controller;


import com.desafio_itau.transacao_api.business.service.EstatisticaService;
import com.desafio_itau.transacao_api.controller.dto.EstatisticasResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticasController estatisticasController;
    @Mock
    EstatisticaService estatisticaService;
    MockMvc mockMvc;
    EstatisticasResponseDto estatistica;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticasController).build();
        estatistica = new EstatisticasResponseDto(1L,20.0,20.0,20.0,20.0);
    }

    @Test
    void buscarEstatisticasComSucesso() throws Exception {
        when(estatisticaService.calcularEstatisticaTransacoes(60)).thenReturn(estatistica);
        mockMvc.perform(get("/estatistica")
                .param("IntervaloBusca", "60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatistica.count()));
    }
}
