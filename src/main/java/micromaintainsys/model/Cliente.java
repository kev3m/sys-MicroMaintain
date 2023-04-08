package micromaintainsys.model;

import java.util.ArrayList;

public class Cliente {
    private int clienteID;
    private String nome;
    private String endereco;
    private String telefone;
    private ArrayList<Ordem> ordens;

    public Cliente(String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ordens = new ArrayList<>();
    }

    public void setNome(String nome) {this.nome = nome;}
    public void setClienteID(int id) {this.clienteID = id;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setOrdem(Ordem ordem){this.ordens.add(ordem);}


    public int getId() { return clienteID; }
    public String getName() { return nome; }
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
    public ArrayList<Ordem> getOrdens() {return ordens;}



}
