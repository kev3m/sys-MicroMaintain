package micromaintainsys.model;

import java.util.ArrayList;

/**
 Classe que representa um cliente.
 */
public class Cliente {

    /**

     Identificador único do cliente.
     */
    private int clienteID;
    /**

     Nome do cliente.
     */
    private String nome;
    /**

     Endereço do cliente.
     */
    private String endereco;
    /**

     Número de telefone do cliente.
     */
    private String telefone;
    /**

     Lista de ordens feitas pelo cliente.
     */
    private ArrayList<Ordem> ordens;
    /**

     Construtor da classe Cliente.
     @param nome o nome do cliente.
     @param endereco o endereço do cliente.
     @param telefone o número de telefone do cliente.
     */
    public Cliente(String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ordens = new ArrayList<>();
    }
    /**

     Método para definir o nome do cliente.
     @param nome o nome do cliente.
     */
    public void setNome(String nome) {this.nome = nome;}
    /**

     Método para definir o identificador único do cliente.
     @param id o identificador único do cliente.
     */
    public void setClienteID(int id) {this.clienteID = id;}
    /**

     Método para definir o endereço do cliente.
     @param endereco o endereço do cliente.
     */
    public void setEndereco(String endereco) {this.endereco = endereco;}
    /**

     Método para definir o número de telefone do cliente.
     @param telefone o número de telefone do cliente.
     */
    public void setTelefone(String telefone) {this.telefone = telefone;}
    /**

     Método para adicionar uma nova ordem feita pelo cliente.
     @param ordem a ordem feita pelo cliente.
     */
    public void setOrdem(Ordem ordem){this.ordens.add(ordem);}
    /**

     Método para obter o identificador único do cliente.
     @return o identificador único do cliente.
     */
    public int getId() { return clienteID; }
    /**

     Método para obter o nome do cliente.
     @return o nome do cliente.
     */
    public String getName() { return nome; }
    /**

     Método para obter o endereço do cliente.
     @return o endereço do cliente.
     */
    public String getEndereco() {return endereco;}
    /**

     Método para obter o número de telefone do cliente.
     @return o número de telefone do cliente.
     */
    public String getTelefone() {return telefone;}
    /**

     Método para obter a lista de ordens feitas pelo cliente.
     @return a lista de ordens feitas pelo cliente.
     */
    public ArrayList<Ordem> getOrdens() {return ordens;}
}