package refactory.ddd.agenda.infrastructure;

import refactory.ddd.agenda.domain.entities.Agenda;
import refactory.ddd.agenda.domain.entities.Local;
import refactory.ddd.agenda.domain.entities.Orgao;
import refactory.ddd.agenda.domain.entities.Servico;
import refactory.ddd.agenda.domain.valueObjects.AgendaId;
import refactory.ddd.agenda.domain.valueObjects.LocalId;
import refactory.ddd.agenda.domain.valueObjects.OrgaoId;
import refactory.ddd.agenda.domain.valueObjects.ServicoId;

import java.util.List;

public interface AgendaRepository {
    void refresh(Agenda agenda);

    void save(List<Agenda> gradeHorarios);

    void save(Agenda agenda);

    List<Orgao> listAllOrgao();

    List<Local> listLocalByOrgao(OrgaoId orgaoId);

    List<Servico> listServicoByOrgao(OrgaoId orgaoId);

    List<Agenda> listAgendaByLocal(LocalId localId);

    Agenda findAgendaById(AgendaId agendaId);

    Servico findServicoById(ServicoId servicoId);
}
