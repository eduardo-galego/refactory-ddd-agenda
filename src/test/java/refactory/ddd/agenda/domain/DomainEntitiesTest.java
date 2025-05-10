package refactory.ddd.agenda.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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

@SpringBootTest
public class DomainEntitiesTest {

    @Test
    public void successCreateOrgao() {
        // Given

        // When
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // Then
        assertThat(orgao).isNotNull();
        Assertions.assertThat(orgao.getOrgaoId()).isNotNull();
        Assertions.assertThat(orgao.getOrgaoId().getId()).isNotNull();
        Assertions.assertThat(orgao.getOrgaoId().getId()).isEqualTo(1);
        Assertions.assertThat(orgao.getDescricao()).isNotNull();
        Assertions.assertThat(orgao.getDescricao().getDescricao()).isEqualTo("Detran");
    }

    @Test
    public void successCreateLocal() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // When
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);

        // Then
        assertThat(local).isNotNull();
        Assertions.assertThat(local.getLocalId()).isNotNull();
        Assertions.assertThat(local.getLocalId().getId()).isNotNull();
        Assertions.assertThat(local.getLocalId().getId()).isEqualTo(10);
        Assertions.assertThat(local.getDescricao()).isNotNull();
        Assertions.assertThat(local.getDescricao().getDescricao()).isEqualTo("Poupatempo Santo Amaro");
        assertThat(local.getOrgao()).isEqualTo(orgao);
    }

    @Test
    public void successCreateService() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // When
        final Servico servico = new Servico(new ServicoId(100), new DescricaoCurta("Renovacao de CNH"), orgao);

        // Then
        assertThat(servico).isNotNull();
        Assertions.assertThat(servico.getServicoId()).isNotNull();
        Assertions.assertThat(servico.getServicoId().getId()).isNotNull();
        Assertions.assertThat(servico.getServicoId().getId()).isEqualTo(100);
        Assertions.assertThat(servico.getDescricao()).isNotNull();
        Assertions.assertThat(servico.getDescricao().getDescricao()).isEqualTo("Renovacao de CNH");
        assertThat(servico.getOrgao()).isEqualTo(orgao);
    }

    @Test
    public void successCreateCidadao() {
        // Given
        final NomeCompleto nomeCompleto = new NomeCompleto("Nome completo");
        final CPF cpf = new CPF("231.254.589-66");

        // When
        final Cidadao cidadao = new Cidadao(nomeCompleto, cpf);

        // Then
        assertThat(cidadao).isNotNull();
        assertThat(cidadao.getNomeCompleto()).isNotNull();
        assertThat(cidadao.getNomeCompleto().getNomeCompleto()).isEqualTo("Nome completo");
        assertThat(cidadao.getCpf()).isNotNull();
        assertThat(cidadao.getCpf().getNumero()).isEqualTo("23125458966");
    }

    @Test
    public void successCreateAgenda() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);
        final Horario horario = new Horario(LocalDateTime.now().plus(1, ChronoUnit.DAYS));

        // When
        final Agenda agenda = new Agenda(local, orgao, horario);

        // Then
        assertThat(agenda).isNotNull();
        assertThat(agenda.getAgendaId()).isNotNull();
        assertThat(agenda.getOrgao()).isNotNull();
        assertThat(agenda.getOrgao()).isEqualTo(orgao);
        assertThat(agenda.getLocal()).isNotNull();
        assertThat(agenda.getLocal()).isEqualTo(local);
        Assertions.assertThat(agenda.getAgendaStatus()).isEqualTo(AgendaStatusEnum.VAGO);
    }

    @Test
    public void successCreateReserva() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);
        final Horario horario = new Horario(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        final Agenda agenda = new Agenda(local, orgao, horario);
        final Servico servico = new Servico(new ServicoId(100), new DescricaoCurta("Renovacao de CNH"), orgao);
        final NomeCompleto nomeCompleto = new NomeCompleto("Nome completo");
        final CPF cpf = new CPF("231.254.589-66");
        final Cidadao cidadao = new Cidadao(nomeCompleto, cpf);

        // When
        final Reserva reserva = new Reserva(agenda, servico, cidadao);

        // Then
        assertThat(reserva).isNotNull();
        Assertions.assertThat(reserva.getReservaId()).isNotNull();
        Assertions.assertThat(reserva.getCidadao()).isNotNull();
    }
}
