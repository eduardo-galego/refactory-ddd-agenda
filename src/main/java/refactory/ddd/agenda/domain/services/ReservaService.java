package refactory.ddd.agenda.domain.services;

import refactory.ddd.agenda.domain.entities.Reserva;
import refactory.ddd.agenda.domain.valueObjects.AgendaId;
import refactory.ddd.agenda.domain.valueObjects.Cidadao;
import refactory.ddd.agenda.domain.valueObjects.ServicoId;

public interface ReservaService {

    Reserva reservar(AgendaId agendaId, ServicoId servicoId, Cidadao cidadao);

}
