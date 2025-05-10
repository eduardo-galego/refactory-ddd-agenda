package refactory.ddd.agenda.domain;

import refactory.ddd.agenda.domain.services.ReservaService;
import refactory.ddd.agenda.domain.services.impl.ReservaServiceImpl;
import refactory.ddd.agenda.infrastructure.AgendaRepository;
import refactory.ddd.agenda.infrastructure.ReservaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import refactory.ddd.agenda.domain.entities.Agenda;
import refactory.ddd.agenda.domain.entities.Local;
import refactory.ddd.agenda.domain.entities.Orgao;
import refactory.ddd.agenda.domain.entities.Reserva;
import refactory.ddd.agenda.domain.entities.Servico;
import refactory.ddd.agenda.domain.valueObjects.AgendaStatusEnum;
import refactory.ddd.agenda.domain.valueObjects.CPF;
import refactory.ddd.agenda.domain.valueObjects.Cidadao;
import refactory.ddd.agenda.domain.valueObjects.DescricaoCurta;
import refactory.ddd.agenda.domain.valueObjects.Horario;
import refactory.ddd.agenda.domain.valueObjects.LocalId;
import refactory.ddd.agenda.domain.valueObjects.NomeCompleto;
import refactory.ddd.agenda.domain.valueObjects.OrgaoId;
import refactory.ddd.agenda.domain.valueObjects.ServicoId;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DomainServicesTest {

    private final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));
    private final Servico servico = new Servico(new ServicoId(100), new DescricaoCurta("Renovacao de CNH"), orgao);
    private final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);
    private final Horario horario = new Horario(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
    private final Agenda agenda = new Agenda(local, orgao, horario);

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService = new ReservaServiceImpl(agendaRepository, reservaRepository);

    @BeforeEach
    void setMockOutput() {
        when(agendaRepository.findServicoById(servico.getServicoId())).thenReturn(servico);
        when(agendaRepository.findAgendaById(agenda.getAgendaId())).thenReturn(agenda);
    }

    @Test
    void testGet() {
        // Given
        final NomeCompleto nomeCompleto = new NomeCompleto("Nome completo");
        final CPF cpf = new CPF("231.254.589-66");
        final Cidadao cidadao = new Cidadao(nomeCompleto, cpf);

        // When
        final Reserva reserva = reservaService.reservar(agenda.getAgendaId(), servico.getServicoId(), cidadao);

        // Then
        assertThat(reserva).isNotNull();
        Assertions.assertThat(reserva.getReservaId()).isNotNull();
        Assertions.assertThat(reserva.getCidadao()).isNotNull();
        Assertions.assertThat(reserva.getCidadao()).isNotNull();
        assertThat(reserva.getAgenda()).isNotNull();
        Assertions.assertThat(reserva.getAgenda().getAgendaStatus()).isEqualTo(AgendaStatusEnum.AGENDADO);
    }

}
