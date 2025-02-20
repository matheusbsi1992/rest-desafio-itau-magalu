package test.mockscheduling;

import br.com.desafio.magalu.dto.SchedulingDTO;
import br.com.desafio.magalu.mapper.SchedulingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class MockScheduling {

    public MockScheduling() {
    }

    public SchedulingDTO createScheduling(String nomeDestinatario) {

        SchedulingDTO schedulingDTO = new SchedulingDTO();

        schedulingDTO.setMensagemDeEntregaDTO("Olá, Tudo bem com você ?");
        schedulingDTO.setStatusMensagemDTO("Enviado");
        schedulingDTO.setNomeDoDestinatarioDTO(nomeDestinatario);
        schedulingDTO.setDataHoraAgendamentoDTO(LocalDateTime.now());

        return schedulingDTO;
    }
}