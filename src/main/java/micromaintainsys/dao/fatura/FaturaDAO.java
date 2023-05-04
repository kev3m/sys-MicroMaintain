package micromaintainsys.dao.fatura;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Fatura;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import static micromaintainsys.utils.FileUtils.*;

/**
 * Classe que implementa a interface InterfaceFatura e fornece uma implementação para as operações de
 * acesso a dados relacionados à fatura.
 */

public class FaturaDAO implements InterfaceFatura {
    /**
     Hashtable que mapeia IDs de faturas para as faturas correspondentes.
     */
    static Hashtable<Integer, Fatura> faturasCadastradas = new Hashtable<>();
    /**
     Contador estático usado para gerar IDs únicos para cada nova fatura criada.
     */
    private static int idCounter = 0;
    private static final String FILE_NAME = "faturas.bin";
    private static final String FILE_PATH = initFilePath();

    private static String initFilePath() {
        return getFilePath(FILE_NAME);
    }

    public FaturaDAO(){
        Object obj = carregaDados(FILE_PATH);
        faturasCadastradas = obj == null? new Hashtable<>() : (Hashtable<Integer, Fatura>) obj;
        idCounter = proximoID();
    }
    public int proximoID(){
        Enumeration<Integer> keys = faturasCadastradas.keys();
        int max = -1;
        while (keys.hasMoreElements()){
            int key = keys.nextElement();
            if (key > max){
                max = key;
            }
        }
        return max + 1;
    }


    /**
     Cria uma fatura com o ID da ordem especificada e o valor total especificado.
     @param ordemID ID da ordem correspondente a esta fatura.
     @param valorTotal Valor total da fatura.
     @return Nova fatura criada.
     */
    public Fatura cria(int ordemID, double valorTotal){
        Fatura novaFatura = new Fatura(ordemID, valorTotal);
        novaFatura.setFaturaID(idCounter);
        faturasCadastradas.put(idCounter, novaFatura);
        idCounter++;
        salvaDados(faturasCadastradas, FILE_PATH);
        return novaFatura;
    }
    /**
     Obtém a fatura correspondente ao ID da ordem especificada.
     @param ordemID ID da ordem correspondente à fatura.
     @return Fatura correspondente ao ID da ordem especificada.
     */
    public Fatura pegaPorId(int ordemID) {return faturasCadastradas.get(ordemID);}
    /**
     Atualiza as informações da fatura especificada.
     @param fatura Fatura a ser atualizada.
     @return True se a atualização for bem-sucedida, false caso contrário.
     */
    public boolean atualiza(Fatura fatura){
        salvaDados(faturasCadastradas, FILE_PATH);
        return true;
    }
    /**
     Remove a fatura correspondente ao ID especificado.
     @param faturaID ID da fatura a ser removida.
     @return True se a remoção for bem-sucedida, false caso contrário.
     */

    public boolean remove(int faturaID) {
        Fatura result = faturasCadastradas.remove(faturaID);
        if (result != null){
            salvaDados(faturasCadastradas, FILE_PATH);
            return true;
        }
        return false;
    }
    /**
     Retorna uma lista de todas as faturas cadastradas.
     @return Lista de todas as faturas cadastradas.
     */
    public ArrayList<Fatura> pegaTodas(){
        return (new ArrayList<Fatura>(faturasCadastradas.values()));
    }
    /**
     Reseta o contador de IDs das faturas para zero.
     */
    public void resetIDCounter(){
        idCounter = 0;
    }

}

