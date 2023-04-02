package micromaintainsys.dao.ordem;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Tecnico;

import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class OrdemFakeDAO {
    static Hashtable<Integer, Ordem> ordensCadastradas;
    private static int idCounter = 0;


}
