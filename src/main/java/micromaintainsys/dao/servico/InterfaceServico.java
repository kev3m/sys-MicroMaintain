package micromaintainsys.dao.servico;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.CategoriaServico;
import micromaintainsys.model.Peca;
import micromaintainsys.model.Servico;

public interface InterfaceServico extends CRUD<Servico> {
    public int cria(CategoriaServico categoriaServico, double valor, Peca peca, String descricao, int ordemID);
}


