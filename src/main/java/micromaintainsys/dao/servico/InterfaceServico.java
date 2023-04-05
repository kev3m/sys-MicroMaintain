package micromaintainsys.dao.servico;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.CategoriaServico;
import micromaintainsys.model.Peca;
import micromaintainsys.model.Servico;

import java.util.ArrayList;

public interface InterfaceServico extends CRUD<Servico> {
    public Servico cria(CategoriaServico categoriaServico, double valor, Peca peca, String descricao, int ordemID);
    public <T> ArrayList<Servico> pegaTodosPorOrdemID(int ordemID);
}


