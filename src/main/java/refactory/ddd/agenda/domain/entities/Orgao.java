package refactory.ddd.agenda.domain.entities;

import refactory.ddd.agenda.domain.valueObjects.DescricaoCurta;
import refactory.ddd.agenda.domain.valueObjects.OrgaoId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "orgaoId")
public class Orgao {

    @NonNull
    private OrgaoId orgaoId;

    @NonNull
    private DescricaoCurta descricao;

    public Orgao(OrgaoId orgaoId, DescricaoCurta descricao) {
        this.orgaoId = orgaoId;
        this.descricao = descricao;
    }
}
