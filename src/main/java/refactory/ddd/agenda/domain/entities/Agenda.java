package refactory.ddd.agenda.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import refactory.ddd.agenda.domain.valueObjects.AgendaId;
import refactory.ddd.agenda.domain.valueObjects.AgendaStatusEnum;
import refactory.ddd.agenda.domain.valueObjects.Horario;

@Value
@EqualsAndHashCode(of = "agendaId")
public class Agenda {

    @NonNull
    private AgendaId agendaId;
    @NonNull
    private Orgao orgao;
    @NonNull
    private Local local;
    @NonNull
    private Horario horario;
    @NonNull @NonFinal
    private AgendaStatusEnum agendaStatus;

    public Agenda(Local local, Orgao orgao, Horario horario) {
        this.agendaId = new AgendaId();
        this.orgao = orgao;
        this.local = local;
        this.horario = horario;
        this.agendaStatus = AgendaStatusEnum.VAGO;
    }

    public void reservar() {
        if (!AgendaStatusEnum.VAGO.equals(this.getAgendaStatus())) {
            throw new IllegalArgumentException("Horario para agendamento nao disponivel");
        }
        this.agendaStatus = AgendaStatusEnum.AGENDADO;
    }
}

