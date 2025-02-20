package br.com.desafio.magalu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agendamento")
@Table(name = "agendamento", schema = "magalu")
public class Scheduling {

    @Id
    @Column(name = "id_agendamento", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_agendamento;

    @Column(name = "nome_destinatario_agendamento")
    private String nome_destinatario_agendamento;

    @Column(name = "data_cadastramento_agendamento")
    private LocalDateTime data_cadastramento_agendamento;

    @Column(name = "data_cancelamento_agendamento")
    private LocalDateTime data_cancelamento_agendamento;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime data_hora_agendamento;

    @Column(name = "mensagem_de_entrega_agendamento")
    private String mensagem_de_entrega_agendamento;

    @Column(name = "status_mensagem_agendamento")
    private String status_mensagem_agendamento;

    @PrePersist
    public void prePersist() {
        this.data_cadastramento_agendamento = LocalDateTime.now();
    }

   /* @PreUpdate
    public void preUpdate() {
        this.status_mensagem_agendamento = "Cancelado";
        this.data_cancelamento_agendamento = LocalDateTime.now();
    }*/


}