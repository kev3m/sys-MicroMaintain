package micromaintainsys.dao.fatura;

import micromaintainsys.model.Fatura;

import java.util.ArrayList;
import java.util.Hashtable;

public class FaturaFakeDAO implements InterfaceFatura {
    static Hashtable<Integer, Fatura> faturasCadastradas = new Hashtable<>();
    private static int idCounter = 0;

    public Fatura cria(int ordemID, double valorTotal){
        Fatura novaFatura = new Fatura(ordemID, valorTotal);
        novaFatura.setFaturaID(idCounter);
        faturasCadastradas.put(idCounter, novaFatura);
        idCounter++;
        return novaFatura;
    }

    public Fatura pegaPorId(int ordemID) {return faturasCadastradas.get(ordemID);}

    public boolean atualiza(Fatura fatura){
        return true;
    }
    public boolean remove(int faturaID) {
        Fatura result = faturasCadastradas.remove(faturaID);
        if (result != null){
            return true;
        }
        return false;
    }
    public ArrayList<Fatura> pegaTodas(){
        return (new ArrayList<Fatura>(faturasCadastradas.values()));
    }
    public void resetIDCounter(){
        idCounter = 0;
    }

}

