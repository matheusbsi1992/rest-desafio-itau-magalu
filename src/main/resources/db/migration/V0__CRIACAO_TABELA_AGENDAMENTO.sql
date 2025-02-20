create table magalu.agendamento(

    id_agendamento uuid default gen_random_uuid(),

    nome_destinatario_agendamento varchar,

    data_cadastramento_agendamento timestamp,

    data_cancelamento_agendamento timestamp,

    data_hora_agendamento timestamp,

    mensagem_de_entrega_agendamento varchar,

    status_mensagem_agendamento varchar,

    primary key (id_agendamento)

);