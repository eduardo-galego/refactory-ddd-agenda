package refactory.ddd.agenda.domain.services;

import refactory.ddd.agenda.domain.entities.Local;
import refactory.ddd.agenda.domain.entities.Orgao;

import java.time.LocalDate;

public interface GerenciamentoAgendaService {

    void criarGradeHorarios(LocalDate data, Orgao orgao, Local local);
}
