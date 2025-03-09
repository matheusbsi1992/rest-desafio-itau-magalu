package br.com.desafio.magalu.mapper;

import br.com.desafio.magalu.dto.SchedulingDTO;
import br.com.desafio.magalu.model.Scheduling;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-08T23:17:36-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class SchedulingMapperImpl implements SchedulingMapper {

    @Override
    public SchedulingDTO schedulingForSchedulingDTO(Scheduling scheduling) {
        if ( scheduling == null ) {
            return null;
        }

        SchedulingDTO schedulingDTO = new SchedulingDTO();

        schedulingDTO.setNomeDoDestinatarioDTO( scheduling.getNome_destinatario_agendamento() );
        schedulingDTO.setMensagemDeEntregaDTO( scheduling.getMensagem_de_entrega_agendamento() );
        schedulingDTO.setDataHoraAgendamentoDTO( scheduling.getData_hora_agendamento() );
        schedulingDTO.setDataHoraCancelamentoDTO( scheduling.getData_cancelamento_agendamento() );
        schedulingDTO.setStatusMensagemDTO( scheduling.getStatus_mensagem_agendamento() );

        return schedulingDTO;
    }

    @Override
    public Scheduling schedulingDTOForScheduling(SchedulingDTO schedulingDTO) {
        if ( schedulingDTO == null ) {
            return null;
        }

        Scheduling scheduling = new Scheduling();

        scheduling.setNome_destinatario_agendamento( schedulingDTO.getNomeDoDestinatarioDTO() );
        scheduling.setMensagem_de_entrega_agendamento( schedulingDTO.getMensagemDeEntregaDTO() );
        scheduling.setData_hora_agendamento( schedulingDTO.getDataHoraAgendamentoDTO() );
        scheduling.setData_cancelamento_agendamento( schedulingDTO.getDataHoraCancelamentoDTO() );
        scheduling.setStatus_mensagem_agendamento( schedulingDTO.getStatusMensagemDTO() );

        return scheduling;
    }
}
