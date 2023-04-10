package micromaintainsys.control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Queue;

import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.InvalidUserException;
import micromaintainsys.exceptions.NotAllowedException;
import micromaintainsys.exceptions.UserNotLoggedInException;
import micromaintainsys.model.*;

/**
 * Controller responsável por prover a interface com o sistema.
 */
public class MainController {
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensCanceladas;
    private ArrayList<Ordem> ordensFinalizadas;
    private ArrayList<OrdemCompra> ordensCompras;
    private ArrayList<Fatura> faturas;
    //Armazena o ID do tecnico logado no sistema
    private Tecnico tecnicoSessao;
    private Estoque estoque;

    public MainController(){
        this.estoque = criaEstoque();
    }
    /*
    Métodos relacionados a TÉCNICOS
    */

    /**
     * Cria um novo técnico.
     * @param nome nome do técnico
     * @param senha senha do técnico
     * @return novo técnico
     * @throws UserNotLoggedInException nenhum usuário logado
     * @throws NotAllowedException usuário não tem permissão para executar
     */
    public Tecnico criaTecnico(String nome, String senha) throws UserNotLoggedInException, NotAllowedException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        else if (!this.tecnicoSessao.isAdm()){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        else{
            return DAO.getTecnicoDAO().cria(nome, senha);
        }
    }

    /**
     * Realiza o login de um técnico.
     * @param id id do técnico
     * @param senha senha do técnico
     * @return  true: login realizado com sucesso, false: não foi possível realizar o login
     * @throws InvalidUserException usuário não existe
     */
    public boolean loginTecnico(int id, String senha) throws InvalidUserException{
        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        Tecnico loginTecnico = DAO.getTecnicoDAO().pegaPorId(id);
        if (loginTecnico == null){
            throw new InvalidUserException(id);
        }
        else if (this.tecnicoSessao == null
                && DAO.getTecnicoDAO().autentica(id, senha)){
            this.tecnicoSessao = loginTecnico;
            return true;
        }
        return false;
    }

    /**
     * Realiza o log out do técnico.
     * @return
     */
    public boolean logoutTecnico(){
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Remove um técnico do sistema.
     * @param tecnicoID id do técnico a ser removido
     * @throws UserNotLoggedInException nenhum usuário logado
     * @throws NotAllowedException usuário não tem permissão para executar
     * @throws InvalidUserException usuário a ser removido não existe
     */
    public void removeTecnico(int tecnicoID) throws InvalidUserException, NotAllowedException, UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        else if (!this.tecnicoSessao.isAdm()){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        else if(DAO.getTecnicoDAO().pegaPorId(tecnicoID) == null){
            throw new InvalidUserException(tecnicoID);
        }
        else{
            DAO.getTecnicoDAO().remove(tecnicoID);
        }
    }

    /*
    Métodos relacionados a CLIENTES
     */

    /**
     * Cria um novo cliente.
     * @param nome nome do cliente
     * @param endereco endereço do cliente
     * @param telefone telefone do cliente
     * @return objeto do novo cliente
     * @throws UserNotLoggedInException nenhum usuário logado
     */
    public Cliente criaCliente(String nome, String endereco, String telefone) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        return DAO.getClienteDAO().cria(nome, endereco, telefone);
    }

    /**
     * Remove um cliente.
     * @param clienteID id do cliente
     * @return
     * @throws UserNotLoggedInException nenhum usuário logado
     */
    public boolean removeCliente(int clienteID) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        if (clienteID >= 0 && DAO.getClienteDAO().remove(clienteID)){
            return true;
        }
        return false;
    }

    /*
    Métodos relacionados a Ordens e Serviços
     */
    public Ordem criaOrdem(int clienteID){
        Ordem novaOrdem = DAO.getOrdemDAO().cria(clienteID);
        this.ordensAbertas.add(novaOrdem);
        return novaOrdem;
    }

    public boolean atribuiOrdem(Ordem ordem, int tecnicoID){
        Tecnico tecnico = DAO.getTecnicoDAO().pegaPorId(tecnicoID);
        /*TODO criar uma exception p/ lidar com isso!*/
        if (tecnico.temOrdemEmAberto())
            return false;
        else{
            tecnico.cadastraOrdem(ordem);
            ordem.setTecnicoID(tecnicoID);
            ordem.setStatus(StatusOrdem.Andamento);
            DAO.getTecnicoDAO().atualiza(tecnico);
            DAO.getOrdemDAO().atualiza(ordem);
            return true;
        }
    }

    public Servico criaServico(CategoriaServico categoria, double valor, String peca, String descricao, int ordemID ){
        return DAO.getServicoDAO().cria(categoria, valor, peca, descricao, ordemID);
    }

    public void encerraServico(int servicoID){
        Servico servico = DAO.getServicoDAO().pegaPorId(servicoID);
        servico.encerraServico();
        DAO.getServicoDAO().atualiza(servico);
    }

    /*Métodos relacionados a Pagamento e Fatura */
    /**
     * Gera a fatura de uma ordem com o valor igual
     * ao da soma dos serviços cadastrados na ordem
     * @param ordem
     * @return
     */
    public Fatura geraFatura(Ordem ordem){
        if (ordem.getFaturaID() > -1){
            return null;
        }
        int ordemID = ordem.getOrdemID();

        ArrayList<Servico> servicosOrdem = DAO.getServicoDAO().pegaTodosPorOrdemID(ordemID);
        double valotTotal = servicosOrdem.stream().mapToDouble(Servico::getValor).sum();
        Fatura fatura = DAO.getFaturaDAO().cria(ordemID, valotTotal);
        ordem.setFaturaID(fatura.getFaturaID());
        return fatura;
    }
    public Pagamento realizaPagamento(TipoPagamento tipo, double valor, int faturaID){
        Fatura fatura = DAO.getFaturaDAO().pegaPorId(faturaID);
        /*TODO tratar caso de o pagamento ser mais que o necessário (vai ter função troco?)*/
        //double restante = fatura.getValorTotal() - fatura.getValorPago();

        fatura.setValorPago(fatura.getValorPago() + valor);
        Pagamento novoPagamento = DAO.getPagamentoDAO().cria(tipo, valor, faturaID);
        return novoPagamento;
    }

    /*
    Métods relacionados a Estoque/OrdemCompra
     */

    public Estoque criaEstoque(){
        //this.estoque = DAO.getEstoqueDAO().cria()
        return DAO.getEstoqueDAO().cria();
    }

    public void compraPeca(String peca, int quantidade, double valorUnitario){
        OrdemCompra novaOrdem = new OrdemCompra(peca, quantidade, valorUnitario);
        this.estoque.criaOrdemCompra(novaOrdem);
        this.estoque.adicionaPeca(peca, quantidade);
        DAO.getEstoqueDAO().atualiza(estoque);
    }

    /*Métodos relacionados a relatórios*/
    public RelatorioServicos geraRelatorioServicos(Calendar inicio, Calendar fim){
        ArrayList<Servico> servicos = DAO.getServicoDAO().pegaTodosPorDataCriacao(inicio, fim);
        return new RelatorioServicos(servicos);
    }
    public RelatorioCompras geraRelatorioCompras(Calendar inicio, Calendar fim){
        return this.estoque.geraRelatorioCompras(inicio, fim);
    }

}
