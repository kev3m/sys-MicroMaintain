package micromaintainsys.dao.servico;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.CategoriaServico;
import micromaintainsys.model.Peca;
import micromaintainsys.model.Servico;

import java.util.ArrayList;
import java.util.Calendar;

public interface InterfaceServico extends CRUD<Servico> {
    public Servico cria(CategoriaServico categoriaServico, double valor, String peca, String descricao, int ordemID);
    public ArrayList<Servico> pegaTodosPorOrdemID(int ordemID);
    public boolean atualiza(Servico servico);
    public ArrayList<Servico> pegaTodosPorDataCriacao(Calendar inicio, Calendar fim);
}


