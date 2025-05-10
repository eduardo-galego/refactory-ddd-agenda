package refactory.ddd.agenda.infrastructure;

import refactory.ddd.agenda.domain.entities.Reserva;

public interface ReservaRepository {

    void save(Reserva reserva);

}
