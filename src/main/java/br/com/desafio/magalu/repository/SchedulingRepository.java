package br.com.desafio.magalu.repository;

import br.com.desafio.magalu.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

    @Query(value = "SELECT U " +
            "FROM agendamento U " +
            "WHERE U.status_mensagem_agendamento = :status_mensagem_agendamento")
    List<Scheduling> returnSchedulingStatus(@Param("status_mensagem_agendamento") String status);

    @Query(value = "SELECT CASE WHEN COUNT(U) > 0 THEN true ELSE false END " +
            "FROM agendamento U " +
            "WHERE " +
            "U.data_hora_agendamento = :data_hora_agendamento AND UPPER(U.status_mensagem_agendamento) <> UPPER('cancelado') ")
    boolean returnSchedulingStatus(@Param("data_hora_agendamento") LocalDateTime dataHoraAgendamento);

    @Query(value = "SELECT U " +
            "FROM agendamento U " +
            "WHERE LOWER(U.nome_destinatario_agendamento ) LIKE LOWER(CONCAT('%', :nome_destinatario_agendamento, '%'))"
            )
    List<Scheduling> returnSchedulingEmail(@Param("nome_destinatario_agendamento") String nomeDestinatarioAgendamento);

}