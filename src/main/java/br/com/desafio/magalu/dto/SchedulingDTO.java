package br.com.desafio.magalu.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDTO {

    @JsonProperty(value = "nomeDestinatario")
    @Email
    private String nomeDoDestinatarioDTO;

    @JsonProperty(value = "dataHoraAgendamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraAgendamentoDTO;

    @JsonProperty(value = "dataHoraCancelamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dataHoraCancelamentoDTO;

    @JsonProperty(value = "mensagem")
    private String mensagemDeEntregaDTO;

    @JsonProperty(value = "status")
    private String statusMensagemDTO;

}