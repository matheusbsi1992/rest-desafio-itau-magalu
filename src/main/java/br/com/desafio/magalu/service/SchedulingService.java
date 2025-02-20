package br.com.desafio.magalu.service;

import br.com.desafio.magalu.dto.SchedulingDTO;
import br.com.desafio.magalu.mapper.SchedulingMapper;
import br.com.desafio.magalu.model.Scheduling;
import br.com.desafio.magalu.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingService {

    private SchedulingRepository schedulingRepository;

    private SchedulingMapper schedulingMapper;

    private SchedulingDTO schedulingDTO;
    private Scheduling scheduling;


    SchedulingService(SchedulingRepository schedulingRepository, SchedulingMapper schedulingMapper) {
        this.schedulingDTO = new SchedulingDTO();
        this.scheduling = new Scheduling();
        this.schedulingRepository = schedulingRepository;
        this.schedulingMapper = schedulingMapper;
    }

    public SchedulingDTO insertScheduling(SchedulingDTO schedulingDTO) {

        var newScheduling = schedulingMapper.schedulingDTOForScheduling(schedulingDTO);

        if (newScheduling.getData_hora_agendamento().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("O agendamento de " + newScheduling.getNome_destinatario_agendamento() + ", não pode ser realizado. Pois está fora do Horário !!!");
        }

        if (schedulingRepository.returnSchedulingStatus(newScheduling.getData_hora_agendamento()) == true) {
            throw new RuntimeException("Já existe um agendamento neste devido horário !!!");
        }

        newScheduling = schedulingRepository.save(newScheduling);

        return schedulingMapper.schedulingForSchedulingDTO(newScheduling);
    }

    public List<SchedulingDTO> schedulingListStatus(String status) {
        return schedulingRepository.
                returnSchedulingStatus(status)
                .stream()
                .map(schedulingMapper::schedulingForSchedulingDTO)
                .toList();
    }

    public List<SchedulingDTO> schedulingListEmail(String emailDestinatario) {
        return schedulingRepository.
                returnSchedulingEmail(emailDestinatario)
                .stream()
                .map(schedulingMapper::schedulingForSchedulingDTO)
                .toList();
    }

    public List<SchedulingDTO> listAll() {
        return schedulingRepository.
                findAll()
                .stream()
                .map(schedulingMapper::schedulingForSchedulingDTO)
                .toList();
    }

    public SchedulingDTO updateScheduling(SchedulingDTO schedulingDTO) {

        var newScheduling = schedulingMapper.schedulingDTOForScheduling(schedulingDTO);

        var newSchedulingFindByNamePersonAndStatus = schedulingRepository.
                findAll()
                .stream().filter(scheduling1 -> scheduling1.getNome_destinatario_agendamento().equals(newScheduling.getNome_destinatario_agendamento())
                        && scheduling1.getData_hora_agendamento().equals(newScheduling.getData_hora_agendamento()))
                .findAny();


                /*= schedulingRepository.returnSchedulingEmailStatus(schedulingDTO.getNomeDoDestinatarioDTO(), (schedulingDTO.getDataHoraAgendamentoDTO()))
                .orElseThrow(() -> new RuntimeException("Destinatário não reconhecido !!!"));*/

        //-- Dados ainda conforme constam
        newSchedulingFindByNamePersonAndStatus.get().setNome_destinatario_agendamento(newSchedulingFindByNamePersonAndStatus.get().getNome_destinatario_agendamento());
        newSchedulingFindByNamePersonAndStatus.get().setData_hora_agendamento(newSchedulingFindByNamePersonAndStatus.get().getData_hora_agendamento());
        newSchedulingFindByNamePersonAndStatus.get().setData_cadastramento_agendamento(newSchedulingFindByNamePersonAndStatus.get().getData_cadastramento_agendamento());
        newSchedulingFindByNamePersonAndStatus.get().setMensagem_de_entrega_agendamento(newSchedulingFindByNamePersonAndStatus.get().getMensagem_de_entrega_agendamento());

        //-- Identifica como Cancelado (PosUpdate) na classe de entidade
        newSchedulingFindByNamePersonAndStatus.get().setStatus_mensagem_agendamento("Cancelado");
        newSchedulingFindByNamePersonAndStatus.get().setData_cancelamento_agendamento(LocalDateTime.now());

        var newSchedulingUpdate = schedulingRepository.save(newSchedulingFindByNamePersonAndStatus.get());

        return schedulingMapper.schedulingForSchedulingDTO(newSchedulingUpdate);
    }

}