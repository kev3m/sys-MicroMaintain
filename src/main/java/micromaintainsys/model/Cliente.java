package micromaintainsys.model;

import java.util.ArrayList;

public class Cliente {
    private int clienteID;
    private String nome;
    private String endereco;
    private String telefone;
    private ArrayList<Ordem> ordens;

    public Cliente(int clienteID, String endereco, String telefone, ArrayList<Ordem> ordens){
        this.clienteID = clienteID;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ordens = ordens;
    }

    public void setNome(String nome) {this.nome = nome;}
    public void setClienteID(int id) {this.clienteID = id;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setOrdem(Ordem ordem){this.ordens.add(ordem);}


    public int getId() { return clienteID; }
    public int getName() { return nome; }
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
    public  getOrdens() {return ordens;}

}
