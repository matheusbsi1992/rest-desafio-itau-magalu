package br.com.desafio.magalu.mapper;


import br.com.desafio.magalu.dto.SchedulingDTO;
import br.com.desafio.magalu.model.Scheduling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.text.DateFormat;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {

    @Mappings({
            @Mapping(source = "nome_destinatario_agendamento", target = "nomeDoDestinatarioDTO"),
            @Mapping(source = "mensagem_de_entrega_agendamento", target = "mensagemDeEntregaDTO"),
            @Mapping(source = "data_hora_agendamento", target = "dataHoraAgendamentoDTO", dateFormat = "dd/MM/yyyy HH:mm:ss"),
            @Mapping(source = "data_cancelamento_agendamento", target = "dataHoraCancelamentoDTO", dateFormat = "dd/MM/yyyy HH:mm:ss"),
            @Mapping(source = "status_mensagem_agendamento", target = "statusMensagemDTO")
    })
    SchedulingDTO schedulingForSchedulingDTO(Scheduling scheduling);

    @Mappings({
            @Mapping(source = "nomeDoDestinatarioDTO", target = "nome_destinatario_agendamento"),
            @Mapping(source = "mensagemDeEntregaDTO", target = "mensagem_de_entrega_agendamento"),
            @Mapping(source = "dataHoraAgendamentoDTO", target = "data_hora_agendamento", dateFormat = "dd/MM/yyyy HH:mm:ss"),
            @Mapping(source = "dataHoraCancelamentoDTO", target = "data_cancelamento_agendamento", dateFormat = "dd/MM/yyyy HH:mm:ss"),
            @Mapping(source = "statusMensagemDTO", target = "status_mensagem_agendamento")
    })
    Scheduling schedulingDTOForScheduling(SchedulingDTO schedulingDTO);

}
