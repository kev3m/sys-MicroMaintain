package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Tecnico;

public interface InterfaceTecnico {
    public Tecnico pegaPorId(int tecnicoID);
    public int cria(String nome, String senha);
    public boolean autentica(int tecnicoID, String senha);
    public boolean remove(int tecnicoID);
    public <T> boolean atualiza(int tecnicoID, String atributo, T valor);
}
