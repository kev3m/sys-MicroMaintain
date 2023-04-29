package micromaintainsys.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Classe que representa um técnico.
 */
public class Tecnico implements Serializable {
    /**
     Definição de categoria administrador.
     */
    private boolean adm;
    /**
     Nome do técnico.
     */
    private String nome;
    /**
     Senha do técnico.
     */
    private String senha;
    /**
     ID do técnico.
     */
    private int tecnicoID;
    /**
     ID da ordem de serviço em andamento.
     */
    private int ordemEmAndamentoID = -1;

    /**
     * Construtor da classe.
     * @param nome nome do técnico
     * @param senha senha do técnico
     */
    public Tecnico(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Modifica o tipo de conta do técnico.
     * @param adm indica se o usuário é administrador (true) ou um técnico normal (false)
     */
    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    /**
     * Modifica o nome do usuário.
     * @param nome novo nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Modifica a senha do usuário.
     * @param senha nova senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Modifica o ID do técnico.
     * @param tecnicoID novo id do técnico.
     */
    public void setTecnicoID(int tecnicoID) {
        this.tecnicoID = tecnicoID;
    }


    /**
     * Indica se o técnico é administrador ou não.
     * @return (true) indica que o usuário é administrador e (false) indica que é um usuário normal.
     */
    public boolean isAdm() {
        return adm;
    }

    /**
     * Retorna o nome do técnico.
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a senha do técnico.
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna o ID do técnico.
     * @return
     */
    public int getTecnicoID() {
        return tecnicoID;
    }
    /**
     * Retorna o ID da ordem de serviço em andamento.
     * @return
     */
    public int getOrdemEmAndamentoID(){
        return ordemEmAndamentoID;
    }
    /**
     * Modifica o ID da ordem de serviço em andamento.
     * @param abertoID novo ID da ordem de serviço em andamento
     */
    public void setOrdemEmAndamentoID(int abertoID){
        this.ordemEmAndamentoID = abertoID;
    }

}
