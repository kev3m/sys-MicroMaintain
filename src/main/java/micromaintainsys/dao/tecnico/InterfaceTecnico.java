package micromaintainsys.dao.tecnico;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;

public interface InterfaceTecnico extends CRUD<Tecnico> {
    public Tecnico cria(String nome, String senha);
    public boolean autentica(int tecnicoID, String senha);
    public Tecnico pegaPorId(int tecnicoID);
    public boolean atualiza(Tecnico tecnico);
    public ArrayList<Tecnico> pegaTodos();

}
