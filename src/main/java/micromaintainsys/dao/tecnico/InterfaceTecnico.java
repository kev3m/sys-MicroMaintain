package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Tecnico;

public interface InterfaceTecnico {
    public Tecnico pegaPorId(int ID);
    public int cria(String nome, String senha);
    public boolean autentica(int ID, String senha);
}
